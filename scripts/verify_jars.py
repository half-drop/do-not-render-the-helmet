#!/usr/bin/env python3
"""Validate the installable artifacts produced by the multi-version build."""

import json
import struct
import zipfile
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
TARGETS = {"1.21.11": 21, "26.2": 25, "26.3": 25}


def main() -> None:
    for minecraft, java in TARGETS.items():
        jars = [
            path
            for path in (ROOT / f"mc{minecraft}" / "build" / "libs").glob("*.jar")
            if not path.name.endswith(("-sources.jar", "-javadoc.jar"))
        ]
        if len(jars) != 1:
            raise ValueError(f"Expected one installable JAR for {minecraft}, found {len(jars)}")

        path = jars[0]
        with zipfile.ZipFile(path) as jar:
            names = set(jar.namelist())
            documents = {
                name: json.loads(jar.read(name))
                for name in names
                if name.endswith(".json")
            }
            metadata = documents["fabric.mod.json"]
            if metadata["id"] != "nohat" or metadata["environment"] != "client":
                raise ValueError(f"Incorrect mod identity or environment in {path.name}")
            if metadata["depends"]["minecraft"] != minecraft:
                raise ValueError(f"Incorrect Minecraft dependency in {path.name}")
            if metadata["depends"]["java"] != f">={java}":
                raise ValueError(f"Incorrect Java dependency in {path.name}")
            expected_name = f"do-not-render-the-helmet-{minecraft}-{metadata['version']}.jar"
            if path.name != expected_name or "${" in metadata["version"]:
                raise ValueError(f"Incorrect artifact version in {path.name}")
            if metadata["icon"] not in names:
                raise ValueError(f"Missing icon in {path.name}")

            mixins = documents["nohat.mixins.json"]
            if not mixins["required"] or mixins["injectors"]["defaultRequire"] != 1:
                raise ValueError(f"Required mixins disabled in {path.name}")
            class_names = [
                entry.replace(".", "/") + ".class"
                for entry in metadata["entrypoints"]["client"]
            ] + [
                mixins["package"].replace(".", "/") + "/" + entry + ".class"
                for entry in mixins["client"]
            ]
            for class_name in class_names:
                class_data = jar.read(class_name)
                if class_data[:4] != b"\xca\xfe\xba\xbe":
                    raise ValueError(f"Invalid class file {class_name}")
                major = struct.unpack(">H", class_data[6:8])[0]
                if major != java + 44:
                    raise ValueError(f"Incorrect Java bytecode version in {class_name}: {major}")
        print(f"Validated {path.name}: Minecraft {minecraft}, Java {java}, JSON and mixins")


if __name__ == "__main__":
    main()
