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
package com.webank.blockchain.data.stash.db.service;


import com.webank.blockchain.data.stash.constants.DBStaticTableConstants;
import com.webank.blockchain.data.stash.db.dao.SysBlock2NoncesInfoMapper;
import com.webank.blockchain.data.stash.db.face.StorageService;
import com.webank.blockchain.data.stash.db.model.SysBlock2NoncesInfo;
import com.webank.blockchain.data.stash.entity.TableDataInfo;
import com.webank.blockchain.data.stash.exception.DataStashException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * SysBlock2NoncesInfoService
 *
 * @Description: SysBlock2NoncesInfoService
 * @author graysonzhang
 * @data 2019-08-08 11:07:41
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class SysBlock2NoncesInfoService extends DBBaseOperation implements StorageService {
	
	@Autowired
	private SysBlock2NoncesInfoMapper mapper;
	
	private static final int SYS_BLOCK_2_NONCE_BATCH = 100;
	
	@Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
	public void createSchema() {
	    mapper.createTable(DBStaticTableConstants.SYS_BLOCK_2_NONCES_TABLE);
		
		String detailTableName = DBStaticTableConstants.SYS_BLOCK_2_NONCES_TABLE + DBStaticTableConstants.SYS_DETAIL_TABLE_POST_FIX;
		mapper.createDetailTable(detailTableName);
	}
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void storeTableData(String tableName, TableDataInfo tableDataInfo) throws DataStashException {
        storage(tableName, tableDataInfo, SysBlock2NoncesInfo.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void batchSave(String tableName, List list) {
        int batchLastIndex = SYS_BLOCK_2_NONCE_BATCH;

        for (int index = 0; index < list.size();) {
            
            if (SYS_BLOCK_2_NONCE_BATCH >= list.size() - index) {
                batchLastIndex = list.size();
                mapper.batchInsert((List<SysBlock2NoncesInfo>)list.subList(index, batchLastIndex));
                break;
            } else {
                mapper.batchInsert((List<SysBlock2NoncesInfo>)list.subList(index, batchLastIndex));
                index = batchLastIndex;
                batchLastIndex = index + (SYS_BLOCK_2_NONCE_BATCH - 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void batchSaveDetail(String tableName, List list) {
        int batchLastIndex = SYS_BLOCK_2_NONCE_BATCH;

        for (int index = 0; index < list.size();) {
            
            if (SYS_BLOCK_2_NONCE_BATCH >= list.size() - index) {
                batchLastIndex = list.size();
                mapper.batchInsertDetail((List<SysBlock2NoncesInfo>)list.subList(index, batchLastIndex));
                break;
            } else {
                mapper.batchInsertDetail((List<SysBlock2NoncesInfo>)list.subList(index, batchLastIndex));
                index = batchLastIndex;
                batchLastIndex = index + (SYS_BLOCK_2_NONCE_BATCH - 1);
            }
        }
    }
}
