package com.example.ai_agent_service.dto;

public class WrapperRequest {
    private String input;
    private String provider;

    public WrapperRequest() {}  // needed for deserialization

    public WrapperRequest(String input) {
        this.input = input;
        this.provider = "openai"; // default
    }

    public WrapperRequest(String input, String provider) {
        this.input = input;
        this.provider = provider;
    }

    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }
    
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
}
