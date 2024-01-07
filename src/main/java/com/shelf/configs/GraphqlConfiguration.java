package com.shelf.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.shelf.services.BookService;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Configuration
public class GraphqlConfiguration {
	
	@Autowired
	private BookService bookService;
	
	@Bean
	public GraphQL graphql() throws IOException{
		SchemaParser schemaParser = new SchemaParser();
		ClassPathResource resource = new ClassPathResource("schema.graphql");
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(resource.getInputStream());
		
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
		.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBook", bookService.graphqlFetchBookById()))
		.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooks", bookService.graphqlFetchBooks()))
		.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("insertBook", bookService.graphqlSaveBook()))
		.build();
		
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphqlSchema =  schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		
		return GraphQL.newGraphQL(graphqlSchema).build();
		
		
		
	}

}
