head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u���f�[�^�}�l�[�W��(WEB3AdminDirSecHostTableDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 ������ (���u) �V�K�쐬
*/

package webbroker3.dirsec;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CreatedTimestampDivDef;
import webbroker3.common.define.WEB3OrderRequestNumberDivDef;
import webbroker3.dirsec.data.HostManagementDao;
import webbroker3.dirsec.data.HostManagementParams;
import webbroker3.dirsec.data.HostManagementRow;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�L���[�e�[�u���f�[�^�}�l�[�W��)<BR>
 * �L���[�e�[�u���̃��R�[�h�Ǘ��������Ȃ��N���X�B<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableDataManager 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableDataManager.class);
    
    /**
     * @@roseuid 442A1C840196
     */
    public WEB3AdminDirSecHostTableDataManager() 
    {
     
    }
    
    /**
     * (create�L���[�e�[�u���ꗗ���)<BR>
     * �L���[�e�[�u���Ǘ�Params���A�L���[�e�[�u���ꗗ����<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@����:�L���[�e�[�u���ꗗList�̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�L���[�e�[�u���ꗗ�N���X�̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E�@@�L���[�e�[�u���ꗗ�I�u�W�F�N�g.�e�[�u����<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@= ����:�L���[�e�[�u���ꗗList.get( i ).get�e�[�u����()�B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�E�@@�L���[�e�[�u���ꗗ�I�u�W�F�N�g.�e�[�u��������<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@= ����:�L���[�e�[�u���ꗗList.get( i ).get�e�[�u��������()�B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g�ɃL���[�e�[�u���ꗗ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �R�j�@@�L���[�e�[�u���ꗗ�N���X�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g<BR>
     *�@@�@@�@@�@@�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �S�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B <BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(�L���[�e�[�u���ꗗ�N���X�^�̔z��I�u�W�F�N�g); <BR>
     * <BR>
     * �T�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B <BR>
     * @@param l_lisHostTableList - (�L���[�e�[�u���ꗗList)<BR>
     * �L���[�e�[�u���ꗗList�B<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableUnit[]
     * @@roseuid 44190A94027D
     */
    public WEB3AdminDirSecHostTableUnit[] createHostTableListInfo(List l_lisHostTableList) 
    {
        final String STR_METHOD_NAME = " createHostTableListInfo(List) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisHostTableUnit = new ArrayList();
        
        //�Q�j�@@����:�L���[�e�[�u���ꗗList�̗v�f���ALoop�����������Ȃ��B
        int l_intTableListLength = 0;
        if (l_lisHostTableList != null)
        {
            l_intTableListLength = l_lisHostTableList.size();
        }
        for (int i = 0; i < l_intTableListLength; i++)
        {
            //�Q�|�P�j�@@�L���[�e�[�u���ꗗ�N���X�̃I�u�W�F�N�g�𐶐�����B
            WEB3AdminDirSecHostTableUnit l_hostTableUnit = new WEB3AdminDirSecHostTableUnit();
            HostManagementParams l_params = (HostManagementParams)l_lisHostTableList.get(i);
            
            //�L���[�e�[�u���ꗗ�I�u�W�F�N�g.�e�[�u����
            //  = ����:�L���[�e�[�u���ꗗList.get( i ).get�e�[�u����()�B
            l_hostTableUnit.tableJpnName = l_params.getHostTableName();
            
            //�L���[�e�[�u���ꗗ�I�u�W�F�N�g.�e�[�u��������
            //  = ����:�L���[�e�[�u���ꗗList.get( i ).get�e�[�u��������()�B
            l_hostTableUnit.tableName = l_params.getHostTablePhysicsName();
            
            //�Q�|�R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g�ɃL���[�e�[�u���ꗗ
            // �I�u�W�F�N�g��add()����B
            l_lisHostTableUnit.add(l_hostTableUnit);
            
        }
        
        //�R�j�@@�L���[�e�[�u���ꗗ�N���X�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g
        //  �̃T�C�Y�Ő�������B
        WEB3AdminDirSecHostTableUnit[] l_tableUnits = 
            new WEB3AdminDirSecHostTableUnit[l_lisHostTableUnit.size()];
        
        //�S�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
        l_lisHostTableUnit.toArray(l_tableUnits);
        
        //�T�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_tableUnits;
    }
    
    /**
     * (create�L���[�e�[�u�����R�[�h�ڍ�)<BR>
     * �L���[�e�[�u�����R�[�hList���A�L���[�e�[�u�����R�[�h�ڍ׌^�z����쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j�@@����:�L���[�e�[�u�����R�[�hList�̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�L���[�e�[�u�����R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
     * <BR>
     *  �@@�Q�|�Q�|�P�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�،���ЃR�[�h = <BR>
     *�@@�@@�@@�@@�@@�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("institution_code")�B<BR>
     * <BR>
     *  �@@�Q�|�Q�|�Q�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���X�R�[�h = <BR>
     *�@@�@@�@@�@@�@@�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("branch_code")�B<BR>
     * <BR>
     *  �@@�Q�|�Q�|�R�j�@@����:���ʃR�[�h�L���t���O == 1(�L��)�̏ꍇ�A<BR>
     *  <BR>
     * �@@�@@�E�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���ʃR�[�h = <BR>
     *�@@�@@�@@�@@�@@�@@����:�L���[�e�[�u�����R�[�hList.get�i i �j.getColumn("order_request_number")�B<BR>
     * <BR>
     *  �@@�Q�|�Q�|�S�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�X�V�O�X�e�[�^�X = <BR>
     *�@@�@@�@@�@@�@@�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("status")�B<BR>
     * <BR>
     *  �@@�Q�|�Q�|�T�j�@@����:�쐬���t�L���t���O == 1(�L��)�̏ꍇ�A<BR>
     * <BR>�@@
     * �@@  �E�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�쐬���t = <BR>
     *�@@�@@�@@�@@�@@�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("created_timestamp")�B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g�ɃL���[�e�[�u�����R�[�h<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�ڍ׃I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �R�j�@@�L���[�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g<BR>
     *�@@�@@�@@�@@�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �S�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B <BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(�L���[�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g); <BR>
     * <BR>
     * �T�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * @@param l_lisHostTableList - (�L���[�e�[�u�����R�[�hList)<BR>
     * �L���[�e�[�u�����R�[�hList�B<BR>
     * @@param l_strIdentityCodeFlag - (���ʃR�[�h�L���t���O)<BR>
     * ���ʃR�[�h�L���t���O�B<BR>
     * <BR>
     * 0�F��<BR>
     * 1�F�L��<BR>
     * @@param l_strCreateDateFlag - (�쐬���t�L���t���O)<BR>
     * �쐬���t�L���t���O�B<BR>
     * <BR>
     * 0�F��<BR>
     * 1�F�L��<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminDirSecHostTableDetail[]
     * @@roseuid 442153AD0254
     */
    public WEB3AdminDirSecHostTableDetail[] createHostTableDetails(
        List l_lisHostTableList, 
        String l_strIdentityCodeFlag, 
        String l_strCreateDateFlag) 
    {
        final String STR_METHOD_NAME = " createHostTableDetails(List, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�I�u�W�F�N�g�̐����B
        List l_lisHostTableDetail = new ArrayList();
        
        //�Q�j�@@����:�L���[�e�[�u�����R�[�hList�̗v�f���ALoop�����������Ȃ��B
        //�Q�j�@@����:�L���[�e�[�u���ꗗList�̗v�f���ALoop�����������Ȃ��B
        int l_intTableListLength = 0;
        if (l_lisHostTableList != null)
        {
            l_intTableListLength = l_lisHostTableList.size();
        }
        for (int i = 0; i < l_intTableListLength; i++)
        {
            //�Q�|�P�j�@@�L���[�e�[�u�����R�[�h�ڍ׃N���X�̃I�u�W�F�N�g�𐶐�����B
            WEB3AdminDirSecHostTableDetail l_hostTableDetail = new WEB3AdminDirSecHostTableDetail();
            Row l_row = (Row)l_lisHostTableList.get(i);
            
            //�Q�|�Q�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B            
            //�Q�|�Q�|�P�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�،���ЃR�[�h = 
            //�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("institution_code")�B
            if(l_row.getColumn("institution_code") !=null){
            	l_hostTableDetail.institutionCode = 
            	l_row.getColumn("institution_code").toString();
            }
            else{
             	l_hostTableDetail.institutionCode = null;
            }
            
            //�Q�|�Q�|�Q�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���X�R�[�h = 
            //  ����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("branch_code")�B
            if(l_row.getColumn("branch_code") != null){
            	l_hostTableDetail.branchCode = l_row.getColumn("branch_code").toString();
            }
            else{
            	l_hostTableDetail.branchCode = null;
            }
            
            //�Q�|�Q�|�R�j�@@����:���ʃR�[�h�L���t���O == 1(�L��)�̏ꍇ�A
            if (WEB3OrderRequestNumberDivDef.EXISTENCE.equals(l_strIdentityCodeFlag))
            {
                //�E�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.���ʃR�[�h = 
                //�@@����:�L���[�e�[�u�����R�[�hList.get�i i �j.getColumn("order_request_number")�B
                if(l_row.getColumn("order_request_number") != null) {
                	l_hostTableDetail.identityCode = 
                	l_row.getColumn("order_request_number").toString();
                }
                else{
                	l_hostTableDetail.identityCode = null;
                }
            }
            
            //�Q�|�Q�|�S�j�@@�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�X�V�O�X�e�[�^�X = 
            //�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("status")�B
            if(l_row.getColumn("status") != null){
            	l_hostTableDetail.beforeStatus = l_row.getColumn("status").toString();
            }
            else{
            	l_hostTableDetail.beforeStatus = null;
            }
            
            //�Q�|�Q�|�T�j�@@����:�쐬���t�L���t���O == 1(�L��)�̏ꍇ�A
            //�L���[�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g.�쐬���t = 
            //�@@����:�L���[�e�[�u�����R�[�hList.get( i ).getColumn("created_timestamp")�B
            if (WEB3CreatedTimestampDivDef.EXISTENCE.equals(l_strCreateDateFlag))
            {
                l_hostTableDetail.createDate = (Timestamp)l_row.getColumn("created_timestamp");
//                WEB3DateUtility.getDate((String) l_row.getColumn("created_timestamp"),"yyyyMMddhh24MI");
            }
            
            //�Q�|�R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g�ɃL���[�e�[�u�����R�[�h
            //  �ڍ׃I�u�W�F�N�g��add()����B
            l_lisHostTableDetail.add(l_hostTableDetail);            
        }        
        //�R�j�@@�L���[�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g
        //�@@�̃T�C�Y�Ő�������B
        WEB3AdminDirSecHostTableDetail[] l_tableDetails = 
            new WEB3AdminDirSecHostTableDetail[l_lisHostTableDetail.size()];
        
        //�S�j�@@toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
        l_lisHostTableDetail.toArray(l_tableDetails);
        
        //�T�j�@@�ϊ������z��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_tableDetails;
    }
    
    /**
     * (get�X�e�[�^�XMap)<BR>
     * �X�V����X�e�[�^�X�l��Map�I�u�W�F�N�g�Ɋi�[���ĕԋp����B<BR>
     * <BR>
     * �P�j�@@Map�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����:�X�e�[�^�X��Map�I�u�W�F�N�g�Ɋi�[����B<BR>
     * �@@�@@put("status" , ����:�X�e�[�^�X)<BR>
     * <BR>
     * �R�j�@@Map�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X�B<BR>
     * @@return Map
     * @@roseuid 4423453B02F0
     */
    public Map getStatusMap(String l_strStatus) 
    {
        final String STR_METHOD_NAME = " getStatusMap(String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@Map�I�u�W�F�N�g�𐶐�����B
        Map l_mapStatus = new HashMap();
        
        //�Q�j�@@����:�X�e�[�^�X��Map�I�u�W�F�N�g�Ɋi�[����B
        //�@@put("status" , ����:�X�e�[�^�X)
        l_mapStatus.put("status", l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
        return l_mapStatus;
    }
    
    /**
     * �w�肳�ꂽ�e�[�u����RowType��ԋp����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@new RowType(����:�e�[�u�������� , ����:�N�G���v���Z�b�T��)��ԋp����B<BR>
     * @@param l_strTablePhysicsName - (�e�[�u��������)<BR>
     * �e�[�u���������B<BR>
     * @@param l_strQueryProcessorName - (�N�G���v���Z�b�T��)<BR>
     * �N�G���v���Z�b�T���B<BR>
     * @@return RowType
     * @@roseuid 44213F0B0060
     */
    public RowType getRowType(
        String l_strTablePhysicsName, 
        String l_strQueryProcessorName) 
    {
        final String STR_METHOD_NAME = " getRowType(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@new RowType(����:�e�[�u�������� , ����:�N�G���v���Z�b�T��)��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return new RowType(l_strTablePhysicsName, l_strQueryProcessorName);
        
    }
    
    /**
     * (get�L���[�e�[�u���Ǘ�)<BR>
     * �w�肵���L���[�e�[�u�����ɊY������s���L���[�e�[�u���Ǘ���茟������B <BR>
     * <BR>
     * �P�j�L���[�e�[�u���Ǘ�Dao.findRowByPk(����:�L���[�e�[�u����)�ɂ��A<BR>
     * �@@�@@�@@�Y������s���擾����B<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * @@param l_strHostTableName - (�L���[�e�[�u����)<BR>
     * �L���[�e�[�u�����B<BR>
     * @@return HostManagementRow
     * @@throws WEB3BaseException
     * @@roseuid 4420FA110389
     */
    public HostManagementRow getHostTableManagement(String l_strHostTableName) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHostTableManagement(String) ";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�L���[�e�[�u���Ǘ�Dao.findRowByPk(����:�L���[�e�[�u����)�ɂ��A
        //�@@�Y������s���擾����B
        try
        {
            HostManagementRow l_managementRow = 
                (HostManagementRow)HostManagementDao.findRowByPk(l_strHostTableName);
            
            //�Q�j�@@�P�j�Ŏ擾�����l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_managementRow;
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�L���[�e�[�u���ꗗ)<BR>
     * �L���[�e�[�u���Ǘ�Params�̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@�L���[�e�[�u���Ǘ���RowType�I�u�W�F�N�g�B<BR>
     * �@@�@@arg1�F�@@�����F��������<BR>
     * �@@�@@arg2�F�@@�����F�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�����F���������f�[�^<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �@@�@@�@@�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�@@�iBUSINESS_ERROR_01037�j<BR>
     * <BR>
     * �R�j �P�j�̌������ʂ�ԋp����B <BR>
     * @@param l_strQueryCondition - (��������)<BR>
     * ��������������B<BR>
     * @@param l_strSortCondition - (�\�[�g����)<BR>
     * �\�[�g����������B<BR>
     * @@param l_objQueryCondDatas - (���������f�[�^)<BR>
     * ���������f�[�^�R���e�i�B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4418E5F90338
     */
    public List getHostTableList(
        String l_strQueryCondition, 
        String l_strSortCondition, 
        Object[] l_objQueryCondDatas 
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getHostTableList(String, String, Object[]) ";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        // [doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        // �@@�@@arg0�F�@@�L���[�e�[�u���Ǘ���RowType�I�u�W�F�N�g�B
        // �@@�@@arg1�F�@@�����F��������
        // �@@�@@arg2�F�@@�����F�\�[�g����
        // �@@�@@arg3�F�@@null
        // �@@�@@arg4�F�@@�����F���������f�[�^
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostManagementRow.TYPE,
                l_strQueryCondition,
                l_strSortCondition,
                null,
                l_objQueryCondDatas
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        // �@@�@@�@@�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v
        //�@@�@@�@@�@@�iBUSINESS_ERROR_01037�j
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        
        //�R�j �P�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (get�L���[�e�[�u�����R�[�h)<BR>
     * �w�肵���L���[�e�[�u���̃��R�[�h���������������ƂɎ擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@�����F�L���[�e�[�u��RowType<BR>
     * �@@�@@arg1�F�@@�����F��������<BR>
     * �@@�@@arg2�F�@@�����F�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�����F���������f�[�^<BR>
     * <BR>
     * �Q�j�@@�������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �@@�@@�@@�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v<BR>
     * �@@�@@�@@�@@�iBUSINESS_ERROR_01037�j<BR>
     * <BR>
     * �R�j�@@�P�j�Ŏ擾�����l��ԋp����B<BR>
     * @@param l_strQueryCondition - (��������)<BR>
     * ���������B<BR>
     * @@param l_objQueryCondDatas - (���������f�[�^)<BR>
     * ���������f�[�^�R���e�i�B<BR>
     * @@param l_hostTableRowType - (�L���[�e�[�u��RowType)<BR>
     * �L���[�e�[�u��RowType�B<BR>
     * @@param l_strSortCondition - (�\�[�g����)<BR>
     * �L���[�e�[�u���\�[�g����<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4422707E004E
     */
    public List getHostTableRecord(
        String l_strQueryCondition,
        Object[] l_objQueryCondDatas,
        RowType l_hostTableRowType,
        String l_strSortCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHostTableRecord(String, Object[], RowType, String) ";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords = new ArrayList();
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        // [doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        // �@@�@@arg0�F�@@�����F�L���[�e�[�u��RowType
        // �@@�@@arg1�F�@@�����F��������
        // �@@�@@arg2�F�@@�����F�\�[�g����
        // �@@�@@arg3�F�@@null
        // �@@�@@arg4�F�@@�����F���������f�[�^
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                l_hostTableRowType,
                l_strQueryCondition,
                l_strSortCondition,
                null,
                l_objQueryCondDatas
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        // �@@�@@�@@�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v
        //�@@�@@�@@�@@�iBUSINESS_ERROR_01037�j
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        
        //�R�j �P�j�̌������ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }
    
    /**
     * (update�L���[�e�[�u�����R�[�h)<BR>
     * �w�肵���L���[�e�[�u���̍X�V�����������Ȃ��B<BR>
     * <BR>
     * �P�j�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * [doUpdateAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@�����F�L���[�e�[�u��RowType<BR>
     * �@@�@@arg1�F�@@�����F��������<BR>
     * �@@�@@arg2�F�@@�����F���������f�[�^<BR>
     * �@@�@@arg3�F�@@�����F�X�V�X�e�[�^�X<BR>
     * <BR>
     * @@param l_strQueryCondition - (��������)<BR>
     * ��������������B<BR>
     * @@param l_objQueryCondDatas - (���������f�[�^)<BR>
     * ���������f�[�^�R���e�i�B<BR>
     * @@param l_hostTableRowType - (�L���[�e�[�u��RowType)<BR>
     * �L���[�e�[�u��RowType�B<BR>
     * @@param l_mapUpdateStatus - (�X�V�X�e�[�^�X)<BR>
     * �X�V�X�e�[�^�XMap�I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44234A4301E6
     */
    public void updateHostTableRecord(
        String l_strQueryCondition, 
        Object[] l_objQueryCondDatas, 
        RowType l_hostTableRowType, 
        Map l_mapUpdateStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateHostTableRecord(String, Object[], RowType, Map) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B 
        // [doUpdateAllQuery()�ɃZ�b�g����p�����[�^] 
        // �@@�@@arg0�F�@@�����F�L���[�e�[�u��RowType
        // �@@�@@arg1�F�@@�����F��������
        // �@@�@@arg2�F�@@�����F���������f�[�^
        // �@@�@@arg3�F�@@�����F�X�V�X�e�[�^�X
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                l_hostTableRowType,
                l_strQueryCondition,
                l_objQueryCondDatas,
                l_mapUpdateStatus
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
