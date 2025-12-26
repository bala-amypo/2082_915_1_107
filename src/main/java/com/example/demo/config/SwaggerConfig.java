@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server server = new Server();
        server.setUrl("https://9096.408procr.amypo.ai/");
        server.setDescription("Production Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Demo API")
                        .version("1.0")
                        .description("Demo Application APIs"))
                .servers(List.of(server));
    }
}
