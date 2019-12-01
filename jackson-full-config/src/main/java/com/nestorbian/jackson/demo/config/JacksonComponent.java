// package com.nestorbian.jackson.demo.config;
//
// import com.fasterxml.jackson.core.JsonGenerator;
// import com.fasterxml.jackson.databind.JsonSerializer;
// import com.fasterxml.jackson.databind.SerializerProvider;
// import com.nestorbian.jackson.demo.enums.CurrCode;
// import org.springframework.boot.jackson.JsonComponent;
//
// import java.io.IOException;
//
// @JsonComponent
// public class JacksonComponent {
//
//     public static class CurrCodeJsonSerializer extends JsonSerializer<CurrCode> {
//         @Override
//         public void serialize(CurrCode currCode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
//                 throws IOException {
//             jsonGenerator.writeString(currCode.getCode());
//         }
//     }
//
// }
