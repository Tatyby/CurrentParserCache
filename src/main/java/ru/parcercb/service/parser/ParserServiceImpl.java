package ru.parcercb.service.parser;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.parcercb.model.CurrencyRateModel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {
    private final RestTemplate restTemplate;
    @Value("${CURRENCY_URL}")
    private String CURRENCY_URL;
    @Value("${currency.nodes.tag}")
    private String currencyNodesTag;
    @Value("${currency.code.tag}")
    private String currencyCodeTag;
    @Value("${rate.tag}")
    private String rateTag;

    @SneakyThrows
    public List<CurrencyRateModel> parserCB() {
        List<CurrencyRateModel> listEntity = new ArrayList<>();
        String xmlResponse = restTemplate.getForObject(CURRENCY_URL, String.class);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(Objects.requireNonNull(xmlResponse).getBytes(StandardCharsets.UTF_8)));
        NodeList currencyNodes = document.getElementsByTagName(currencyNodesTag);
        for (int i = 0; i < currencyNodes.getLength(); i++) {
            Node node = currencyNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String currencyCode = element.getElementsByTagName(currencyCodeTag)
                        .item(0).getTextContent();
                double rate = Double.parseDouble(element.getElementsByTagName(rateTag)
                        .item(0).getTextContent().replace(",", "."));
                listEntity.add(new CurrencyRateModel(currencyCode, rate));
            }
        }
        return listEntity;
    }
}