package bean;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsRequest;

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


        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
        .region(region)
        .credentialsProvider(credentialsProvider)
        .build();

        ListUserPools.listAllUserPools(cognitoClient) ;
        cognitoClient.close();



        // String userPoolName = args[0];
        // CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
        //     .region(Region.US_EAST_1)
        //     .credentialsProvider(ProfileCredentialsProvider.create())
        //     .build();

        // String id = createPool(cognitoClient,userPoolName);
        // System.out.println("User pool ID: " + id);
        // cognitoClient.close();
    }
}