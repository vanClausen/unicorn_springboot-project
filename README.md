# Spring Boot - Practice Project 'date4u' - a unicorn dating app
> Project 'date4u' based on the udemy tutorial series by Christian Ullenboom
> 
> SpringBoot version: 3.4.2
> 
> Java version: 21

## Spring Boot related topics covered:
- Maven
- application.properties
- Spring Boot Configuration with ApplicationContext and ConfigurableApplicationContext
- Annotations (Component, Configuration, Service, Autowired, Bean, Shell[...], Qualifier, Primary, Order, Lazy, 
PreDestroy, PostConstruct, Value, ConfigurationProperties, EventListener, Async)
- Spring Shell
- DI / IoC
- Konfiguration / Environment / Properties / Profiles
- Spring Events (Publisher / Listener / GenericEvents)

---
## Features
### FileSystem
- set root-path to user directory
- create directories if not existent
- read and write files in root-path (byte[])
- get free disk space in root-path

### AppUuidConfig
- use proxy to set UUID for the application with bean

### Shell-Package
#### PromptProviderConfig
- set PromptProvider to custom prompt, depending on the current user (admin|user - atm randomized)

#### FsCommands
> FileSystem based commands
- `minimum_free_disk_space` returns the minimum free disk space in root-path (atm hardcoded)
- `free_disk_space` returns the free disk space in root-path

#### PhotoCommands
> Photo(File)-based commands
- `show_photo [filename]` displays information about the photo (filename, width, height)

### Photo-Package
#### PhotoService
- handle photo files
  - read from FileSystem


