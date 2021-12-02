/**
 * Copyright 2020 Webank.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.blockchain.data.stash.crypto;

import org.fisco.bcos.sdk.crypto.hash.Hash;
import org.fisco.bcos.sdk.crypto.hash.Keccak256;
import org.fisco.bcos.sdk.crypto.signature.ECDSASignature;
import org.fisco.bcos.sdk.utils.Hex;

import java.io.IOException;

/**
 * CryptoService
 *
 * @Description: CryptoService
 * @author maojiayu
 * @data Dec 23, 2019 4:59:45 PM
 *
 */
public class StandardCryptoService implements CyptoInterface {

    private Hash eccHash = new Keccak256();
    private ECDSASignature ecdsaSignature = new ECDSASignature();

    @Override
    public byte[] hash(byte[] src) {
        return eccHash.hash(src);
    }

    @Override
    public String hash(String src) {
        return eccHash.hash(src);
    }

    @Override
    public boolean verify(byte[] data, String signature, String nodeKey) throws IOException {
        return ecdsaSignature.verify(nodeKey, Hex.toHexString(data), signature);
    }
}
