# BIC Shield Compat

A lightweight server side Minecraft 1.20.1 Forge compatibility mod that fixes Born in Chaos mobs missing Forge entity tags for shield users.

## The Problem

Born in Chaos adds mobs like the Skeleton Thrasher and Door Knight that use custom shield implementations. These mobs are missing the Forge entity tags that other mods use to detect shield users. This means mods like Tinkers Construct cannot recognize BIC mobs as shield users and cannot interact with their shields correctly.

## The Fix

At runtime BIC Shield Compat injects the missing forge:shield_users and forge:entities/shield_users tags onto the affected BIC entity types. This restores compatibility with all mods that check those tags.

## Affected Mobs

Skeleton Thrasher and Door Knight.

## Requirements

Minecraft 1.20.1, Forge 47.x. Born in Chaos and Tinkers Construct are soft dependencies. The mod loads safely without either present.

## Installation

Place the jar in your mods folder alongside Born in Chaos and Tinkers Construct. No configuration needed.

## Compatibility

Fixes compatibility with any mod that checks forge:shield_users or forge:entities/shield_users entity tags, including but not limited to Tinkers Construct.

## Server Side Only

This mod only needs to be installed on the server. Clients do not need it.

## License

MIT, Copyright 2026 Nightwielder23. https://github.com/Nightwielder23/bic-shield-compat/blob/main/LICENSE

## Source Code

https://github.com/Nightwielder23/bic-shield-compat

## Author

Made by Nightwielder23: https://github.com/Nightwielder23