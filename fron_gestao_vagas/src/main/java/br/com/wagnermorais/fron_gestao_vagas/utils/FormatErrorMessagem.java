package br.com.wagnermorais.fron_gestao_vagas.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessagem {

    public static String formatErrorMessagem(String message){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(message);
            if (rootNode.isArray()) {
                return formatArrayErrorMessage(rootNode);
            }
            return rootNode.asText();
        } catch (Exception e) {
            return message;
        }
    }

    public static String formatArrayErrorMessage(JsonNode arrayNode){

        StringBuilder formatMessage  = new StringBuilder();

        for(JsonNode node : arrayNode){
            formatMessage.append("- ").append(node.get("message").asText()).append("\n");

        }
        return formatMessage.toString();
    }
    
}
