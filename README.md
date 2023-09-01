# Spigot Minecraft Plugin - HardcorePlus

## Description

DeathBan is a Minecraft Spigot plugin designed to add a unique gameplay mechanic to your server. When players die, they will be temporarily banned from the server for a specified duration. This adds an extra layer of challenge and excitement to your Minecraft server.

## Features

- Temporary banning of players upon death.
- Configurable ban duration.
- Customizable ban message.
- Player-friendly messages and notifications.

## Installation

1. Download the latest release of DeathBan from the [Releases](https://github.com/yourusername/deathban/releases) page.
2. Place the downloaded JAR file into the `plugins` folder of your Spigot server.
3. Restart your server to load the plugin.

## Configuration

You can customize DeathBan by editing the `config.yml` file located in the `plugins/DeathBan` folder. Here are some of the configurable options:

- `ban-duration`: The duration of the ban in minutes.
- `ban-message`: The message displayed to banned players.

Example `config.yml`:

```yaml
ban-duration: 60
ban-message: "You have been banned for {duration} minutes."
