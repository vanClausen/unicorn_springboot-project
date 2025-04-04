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
PreDestroy, PostConstruct, Value, ConfigurationProperties, EventListener, Async, NonNull, NonNullApi, NonNullFields,
Nullable, Test, Scheduled, Cacheable, Validated, Valid, NotNull, Past, Max(), Min(), Pattern())randomu
- Spring Shell
- DI / IoC
- Konfiguration / Environment / Properties / Profiles
- Spring Events (Publisher / Listener / GenericEvents)
- Resource
- ConversionService / Converter
- TestDrivenDevelopment
- JUnit, AssertJ, Mockito (@Test, @ExtendWith, @Mock, @BeforeEach, @Spy, @InjectMocks, @SpringBootTest, @TestPropertySource, @MockitoBean, @MockitoSpyBean)
- Scheduling (Spring)
- Proxy / ProxyFactory / MethodInterceptor
- Caching / KeyGenerator / Caching with Caffeine
- Async / Concurrency / (Completable)Future
- Validation
- Retryable / Backoff / Recover / RetryTemplate

---
## Features
### FileSystem
- set root-path to user directory
- create directories if not existent
- read and write files in root-path (byte[])
- get free disk space in root-path

### AppUuidConfig
- use proxy via @Configuration to set UUID for the application with bean

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


