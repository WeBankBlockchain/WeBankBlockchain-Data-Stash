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
package com.webank.blockchain.data.stash.block;

import com.webank.blockchain.data.stash.rlp.ByteUtil;
import com.webank.blockchain.data.stash.rlp.RLP;
import com.webank.blockchain.data.stash.rlp.RLPList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;

/**
 * BlockHeader
 *
 * @Description: BlockHeader
 * @author maojiayu
 * @data Sep 3, 2019 4:18:14 PM
 *
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class BlockHeader {

    /* The SHA3 256-bit hash of the parent block, in its entirety */
    private byte[] parentHash;
    private byte[] stateRoot;
    /*
     * The SHA3 256-bit hash of the root node of the trie structure populated with each transaction in the transaction
     * list portion, the trie is populate by [key, val] --> [rlp(index), rlp(tx_recipe)] of the block
     */
    private byte[] transactionsRoot;
    /*
     * The SHA3 256-bit hash of the root node of the trie structure populated with each transaction recipe in the
     * transaction recipes list portion, the trie is populate by [key, val] --> [rlp(index), rlp(tx_recipe)] of the
     * block
     */
    private byte[] receiptRoot;
    /*
     * The Bloom filter composed from indexable information (logger address and log topics) contained in each log entry
     * from the receipt of each transaction in the transactions list
     */
    private byte[] dbHash;

    private byte[] logsBloom;

    /*
     * A scalar value equal to the number of ancestor blocks. The genesis block has a number of zero
     */
    private BigInteger number;
    /* A scalar value equal to the current limit of gas expenditure per block */
    private BigInteger gasLimit;
    /* A scalar value equal to the total gas used in transactions in this block */
    private BigInteger gasUsed;

    private BigInteger timestamp;

    /*
     * An arbitrary byte array containing data relevant to this block. With the exception of the genesis block, this
     * must be 32 bytes or fewer
     */
    private List<byte[]> extraData;
    /*
     * A 64-bit value which, combined with the mix-hash, proves that a sufficient amount of computation has been carried
     * out on this block
     */
    private BigInteger sealer;

    private List<byte[]> sealerList;

    public BlockHeader(byte[] encoded) {
        this((RLPList) RLP.decode2(encoded).get(0));
    }

    public BlockHeader(RLPList rlpHeader) {

        this.parentHash = rlpHeader.get(0).getRLPData();
        this.stateRoot = rlpHeader.get(1).getRLPData();
        this.transactionsRoot = rlpHeader.get(2).getRLPData();
        this.receiptRoot = rlpHeader.get(3).getRLPData();
        this.dbHash = rlpHeader.get(4).getRLPData();
        this.logsBloom = rlpHeader.get(5).getRLPData();

        byte[] nrBytes = rlpHeader.get(6).getRLPData();
        this.number = ByteUtil.bytesToBigInteger(nrBytes);

        byte[] glBytes = rlpHeader.get(7).getRLPData();
        byte[] guBytes = rlpHeader.get(8).getRLPData();
        byte[] tsBytes = rlpHeader.get(9).getRLPData();

        this.gasLimit = ByteUtil.bytesToBigInteger(glBytes);
        this.gasUsed = ByteUtil.bytesToBigInteger(guBytes);
        this.timestamp = ByteUtil.bytesToBigInteger(tsBytes);

        this.extraData = ((RLPList) rlpHeader.get(10)).getList();
        this.sealer = ByteUtil.bytesToBigInteger(rlpHeader.get(11).getRLPData());
        this.sealerList = ((RLPList) rlpHeader.get(12)).getList();
    }

    public BlockHeader(byte[] parentHash, byte[] stateRoot, byte[] transactionsRoot, byte[] receiptsRoot, byte[] dbHash,
            byte[] logsBloom, BigInteger number, BigInteger gasLimit, BigInteger gasUsed, BigInteger timestamp,
            List<byte[]> extraData, BigInteger sealer, List<byte[]> sealerList) {
        this.parentHash = parentHash;
        this.stateRoot = stateRoot;
        this.transactionsRoot = transactionsRoot;
        this.dbHash = dbHash;
        this.logsBloom = logsBloom;
        this.number = number;
        this.gasLimit = gasLimit;
        this.gasUsed = gasUsed;
        this.timestamp = timestamp;
        this.extraData = extraData;
        this.sealer = sealer;
        this.sealerList = sealerList;
    }

}
