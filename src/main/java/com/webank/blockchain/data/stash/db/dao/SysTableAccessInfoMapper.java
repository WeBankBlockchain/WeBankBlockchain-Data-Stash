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
package com.webank.blockchain.data.stash.db.dao;

import com.webank.blockchain.data.stash.db.model.SysTableAccessInfo;

import java.util.List;


/**
 * SysTableAccessInfoMapper
 *
 * @Description: SysTableAccessInfoMapper
 * @author graysonzhang
 * @data 2019-08-08 18:03:49
 *
 */
public interface SysTableAccessInfoMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTableAccessInfo record);

    SysTableAccessInfo selectByPrimaryKey(Long id);
    
    int insertDetail(SysTableAccessInfo record);
    
    List<SysTableAccessInfo> selectDetailByEntryId(Long id);
    
    int batchInsert(List<SysTableAccessInfo> list);

    int batchInsertDetail(List<SysTableAccessInfo> list);

    void deleteDetailByBlockNum(long num);

    List<SysTableAccessInfo> selectDetailByBlockNum(long num);
}