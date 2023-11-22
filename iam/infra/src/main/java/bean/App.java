package bean;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

class App {
    public static void main(String[] args) {
        System.out.println("Provisioning necessary IAM infra");

        System.out.println("Listing your Amazon DynamoDB tables:\n");
        // ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        EnvironmentVariableCredentialsProvider credentialsProvider = EnvironmentVariableCredentialsProvider.create();

        Region region = Region.CA_CENTRAL_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
               .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        DynamoDBListTables.listAllTables(ddb);
        ddb.close();        
    }
}