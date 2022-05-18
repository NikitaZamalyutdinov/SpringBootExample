package org.ibs.soap.calculator;

import lombok.extern.slf4j.Slf4j;
import org.ibs.soap.calculator.schema.Add;
import org.ibs.soap.calculator.schema.AddResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

/**
 * Client to public SOAP calculator <a href="http://www.dneonline.com/calculator.asmx">http://www.dneonline.com/calculator.asmx</a>
 */
@Slf4j
public class CalculatorClient extends WebServiceGatewaySupport {

    private CalculatorClient() {}

    /**
     * Don't create manually. Use autowired bean instead due to proper configuration.
     *
     * @return
     */
    @Contract(" -> new")
    public static @NotNull CalculatorClient create() {
        return new CalculatorClient();
    }

    public int addNumbers(int a, int b) {
        Add request = new Add();
        request.setIntA(a);
        request.setIntB(b);

        log.info(String.format("Adding numbers (%d, %d)", a, b));

        return ((AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive(getDefaultUri(),
                        request,
                        message -> ((SoapMessage) message).setSoapAction("http://tempuri.org/Add")
                )).getAddResult();
    }
}