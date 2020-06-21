package com.toyproject.resourcemanagement.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.toyproject.resourcemanagement.constants.Constants.*;


@Configuration
@EnableTransactionManagement
@EnableAerospikeRepositories(basePackages = "com.toyproject.resourcemanagement.repository")
public class AerospikeConfig {

    @Bean
    AerospikeTemplate aerospikeTemplate(){
        return new AerospikeTemplate(aerospikeClient(),AEROSPIKE_SERVER_NAMESPACE);
    }

    @Bean
    public AerospikeClient aerospikeClient(){
        ClientPolicy clientPolicy=new ClientPolicy();
        clientPolicy.failIfNotConnected=true;
        return new AerospikeClient(clientPolicy,AEROSPIKE_SERVER_HOST_NAME,AEROSPIKE_SERVER_PORT);
    }
}
