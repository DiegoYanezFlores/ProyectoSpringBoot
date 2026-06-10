package ec.com.quitomarket.security.soap;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SoapJaasCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {

        for (Callback callback : callbacks) {

            if (callback instanceof WSPasswordCallback pc) {

                String username = pc.getIdentifier();

                if ("erp_supermaxi".equals(username)) {

                    pc.setPassword("B2B_Secreto_2026");

                } else if ("sistema_bodega".equals(username)) {

                    pc.setPassword("Bodega_Stock_001");

                } else {

                    throw new SecurityException(
                            "Acceso SOAP denegado. Usuario B2B no registrado: "
                                    + username
                    );
                }

            } else {

                throw new UnsupportedCallbackException(
                        callback,
                        "Tipo de validación de seguridad no soportada"
                );
            }
        }
    }
}