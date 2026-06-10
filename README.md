# BIC Shield Compat

Server-side Minecraft 1.20.1 Forge mod that makes shield-disabling weapons work against Born in Chaos's blocking mobs.

The Skeleton Thrasher and Door Knight don't use shield items; they block by giving themselves Resistance IV for a second whenever they take a hit, so an axe that would normally break a shield does nothing against them. A mixin on their hurt path catches a melee hit from a weapon that can disable shields (vanilla axes and Tinkers' Construct axes, through Forge's `canDisableShield`), strips the Resistance, and puts the mob's block on cooldown for five seconds, leaving other weapons still blocked as before.

The mod needs Minecraft 1.20.1 and Forge 47.x, with Born in Chaos as a soft dependency: the mod loads without it but does nothing until it's installed. Tinkers' Construct is optional, since without it the fix still applies to vanilla axes. Drop the jar in the server mods folder; there is no config, and it only has to be installed on the server.

MIT, Copyright 2026 Nightwielder23. Source: https://github.com/Nightwielder23/bic-shield-compat
