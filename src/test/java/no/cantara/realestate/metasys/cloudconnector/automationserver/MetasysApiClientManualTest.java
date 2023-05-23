package no.cantara.realestate.metasys.cloudconnector.automationserver;

import no.cantara.config.ApplicationProperties;
import no.cantara.realestate.metasys.cloudconnector.MetasysCloudconnectorApplicationFactory;
import no.cantara.realestate.metasys.cloudconnector.MockServerSetup;
import org.slf4j.Logger;

import java.net.URI;

import static junit.framework.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class MetasysApiClientManualTest {
    private static final Logger log = getLogger(MetasysApiClientManualTest.class);

    public static void main(String[] args) throws SdLogonFailedException {
        ApplicationProperties config = new MetasysCloudconnectorApplicationFactory()
                .conventions(ApplicationProperties.builder())
                .buildAndSetStaticSingleton();
        String apiUrl = config.get("sd_api_url");
        MockServerSetup.clearAndSetLoginMock();
        MetasysApiClientRest apiClient = new MetasysApiClientRest(URI.create(apiUrl));
        apiClient.logon("jane-doe","strongPassword");
        assertTrue(apiClient.isHealthy());
    }
}
