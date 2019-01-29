head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�L�[(WEB3SrvRegiServiceUseKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���w�� (���u) �V�K�쐬
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiKeyRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�L�[)<BR>
 * �T�[�r�X���p�L�[�G���e�B�e�B�N���X<BR>                     
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseKey implements BusinessObject 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseKey.class);
       
    /**
     * (�T�[�r�X���p�L�[�s)<BR>
     */
    private SrvRegiKeyParams srvRegiKeyParams;

    /** 
     * (�T�[�r�X���p�L�[)<BR>
     * �R���X�g���N�^<BR>
     * ���I�u�W�F�N�g�𐶐����A<BR>
     * ����.�T�[�r�X���p�L�[Row��this.�T�[�r�X���p�L�[�s�ɐݒ肷��B<BR>
     * @@param l_srvRegiKeyRow - (�T�[�r�X���p�L�[Row)<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3SrvRegiServiceUseKey(SrvRegiKeyRow l_srvRegiKeyRow) throws WEB3BaseException 
    {
        this.srvRegiKeyParams = new SrvRegiKeyParams(l_srvRegiKeyRow);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR> 
     * this.�T�[�r�X���p�L�[�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4133093100F0
     */
    public Object getDataSourceObject() 
    {
        return this.srvRegiKeyParams;
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR> 
     * <BR> 
     * �@@this.�T�[�r�X���p�L�[�s���R�s�[���āA�������e��<BR> 
     * �ʃC���X�^���X���쐬����iclone�j�B<BR>  
     * �쐬�����R�s�[�����g��this.�T�[�r�X���p�L�[�s�ɃZ�b�g����B<BR>
     * @@roseuid 413309310100
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams(this.srvRegiKeyParams);
        this.srvRegiKeyParams = l_srvRegiKeyParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createNew���p�L�[ID)<BR>
     * �V�K�p�̃T�[�r�X���p�L�[ID��ԋp����B<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     *   ���p�L�[��ʋ敪=����.���p�L�[��ʋ敪 
     * [���я�]<BR>
     *   �T�[�r�X���p�L�[ID�@@�~�� <BR>
     * <BR>
     * 2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B<BR>
     * <BR>
     * 3) �������ʂ̌���>0���̏ꍇ�A�������ʂ̐擪�̗v�f�ƂȂ�<BR>
     * �@@�T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[ID( )+1��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strSrvUseKeyType - (���p�L�[��ʋ敪)<BR>
     * @@return long
     * @@roseuid 412F0FBD02B7
     */
    public static long createNewUseKeyId(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewUseKeyId(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType)";
        log.entering(STR_METHOD_NAME);
        
        //1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B
        //[��������]
        //�،���ЃR�[�h=����.�،���ЃR�[�h 
        //�T�[�r�X�敪=����.�T�[�r�X�敪 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and srv_use_key_type = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv,
                l_strSrvUseKeyType
            };
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE,
                l_sbWhere.toString(),
                " srv_use_id desc",
                null,
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME);
        }
        
        long l_lngNewUseKeyId;
        //2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B
        //�T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[ID( )+1��ԋp����B
        if(l_lisRecords.size() == 0)
        {
            l_lngNewUseKeyId = 1;
        }
        else
        {
            SrvRegiKeyRow l_srvRegiKeyRow = (SrvRegiKeyRow)l_lisRecords.get(0);
            l_lngNewUseKeyId = l_srvRegiKeyRow.getSrvUseId() + 1;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lngNewUseKeyId;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p�L�[�s.get�،���ЃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40FCC87403D9
     */
    public String getInstitutionCode() 
    {
        String l_strInstitutionCode = this.srvRegiKeyParams.getInstitutionCode();
        return l_strInstitutionCode;
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p�L�[�s.get�T�[�r�X�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40FCC87500EB
     */
    public String getSrvDiv() 
    {
        String l_strSrvDiv = this.srvRegiKeyParams.getSrvDiv();
        return l_strSrvDiv;
    }
    
    /**
     * (get�T�[�r�X���p�L�[ID)<BR>
     * �T�[�r�X���p�L�[ID��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p�L�[�s.get�T�[�r�X���p�L�[ID()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40FCC9E30020
     */
    public long getSrvUseKeyId() 
    {
        long l_lngSrvUseKeyId = this.srvRegiKeyParams.getSrvUseId();
        return l_lngSrvUseKeyId;
    }
    
    /**
     * (set�T�[�r�X���p�L�[)<BR>
     * �T�[�r�X���p�L�[�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���p�L�[�s.set�T�[�r�X���p�L�[()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p�L�[=����.�T�[�r�X���p�L�[<BR>
     * @@param l_strSrvUseKey - (�T�[�r�X���p�L�[)<BR>
     * @@roseuid 40FCCA170187
     */
    public void setSrvUseKey(String l_strSrvUseKey) 
    {
        this.srvRegiKeyParams.setSrvUseKey(l_strSrvUseKey);
    }
    
    /**
     * (get�T�[�r�X���p�L�[)<BR>
     * �T�[�r�X���p�L�[��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p�L�[�s.get�T�[�r�X���p�L�[()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40FCCAB702DF
     */
    public String getSrvUseKey() 
    {
        String l_strSrvUseKey = this.srvRegiKeyParams.getSrvUseKey();
        return l_strSrvUseKey;
    }
    
    /**
     * (get���p�L�[��ʋ敪)<BR>
     * ���p�L�[��ʋ敪��Ԃ��B<BR> 
     * this.�T�[�r�X���p�L�[�s.get���p�L�[��ʋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     */
    public String getSrvUseKeyType()
    {
        String l_strSrvUseKeyType = this.srvRegiKeyParams.getSrvUseKeyType();
        return l_strSrvUseKeyType;
    }
    
    /**
     * (createNew�T�[�r�X���p�L�[)<BR>
     * �V�K�ɃT�[�r�X���p�L�[�I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �T�[�r�X���p�L�[Params�I�u�W�F�N�g�𐶐����Athis.�T�[�r�X<BR>
     * ���p�L�[Params�ɐݒ肷��B<BR>
     * <BR>
     * 2) �T�[�r�X���p�L�[Params.set�،���ЃR�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �T�[�r�X���p�L�[Params.set�T�[�r�X�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) �T�[�r�X���p�L�[Params.set�T�[�r�X���p�L�[ID()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p�L�[ID=�T�[�r�X���p�L�[.createNew���p�L�[ID( )<BR>
     * �@@�@@[createNew���p�L�[ID�ɓn������]<BR>
     * �@@�@@�@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     *      �T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     *      ���p�L�[��ʋ敪=����.���p�L�[��ʋ敪 <BR>
     * <BR>
     * 5) �T�[�r�X���p�L�[Params.set���p�L�[��ʋ敪()���R�[������B 
     * [����] 
�@@   *    ���p�L�[��ʋ敪=����.���p�L�[��ʋ敪<BR>
     * <BR>
     * 6) �T�[�r�X���p�L�[�̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�T�[�r�X���p�L�[�I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p�L�[Row=���������T�[�r�X���p�L�[Params<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strSrvUseKeyType - (���p�L�[��ʋ敪)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUseKey
     * @@throws WEB3BaseException
     * @@roseuid 413E62EE00FB
     */
    public static WEB3SrvRegiServiceUseKey createNewSrvUseKey(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvUseKey(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType)";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X���p�L�[Params�I�u�W�F�N�g�𐶐����Athis.�T�[�r�X���p�L�[Params�ɐݒ肷��B
        SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams();
        
        //2) �T�[�r�X���p�L�[Params.set�،���ЃR�[�h()���R�[������B
        l_srvRegiKeyParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) �T�[�r�X���p�L�[Params.set�T�[�r�X�敪()���R�[������B
        l_srvRegiKeyParams.setSrvDiv(l_strSrvDiv);
        
        //4) �T�[�r�X���p�L�[Params.set�T�[�r�X���p�L�[ID()���R�[������B
        long l_lngSrvUseId = createNewUseKeyId(l_strInstitutionCode, l_strSrvDiv, l_strSrvUseKeyType);
        l_srvRegiKeyParams.setSrvUseId(l_lngSrvUseId);
        
        //5)�T�[�r�X���p�L�[Params.set���p�L�[��ʋ敪()���R�[������B
        l_srvRegiKeyParams.setSrvUseKeyType(l_strSrvUseKeyType);
        
        //5) �T�[�r�X���p�L�[�̃R���X�g���N�^���R�[�����A���������T�[�r�X���p�L�[�I�u�W�F�N�g��ԋp����B
        WEB3SrvRegiServiceUseKey l_srvRegiServiceUseKey = new WEB3SrvRegiServiceUseKey(l_srvRegiKeyParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiServiceUseKey;
    }
    
    /**
     * (save�T�[�r�X���p�L�[)<BR>
     * this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g��<BR>
     * �����f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���p�L�[�e�[�u�����X�V�iUpdate�j����B<BR>
     * @@roseuid 413E62EE010A
     */
    public void saveSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvRegiKeyParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�X�V���t
        this.srvRegiKeyParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.srvRegiKeyParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew�T�[�r�X���p�L�[)<BR>
     * this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���p�L�[�e�[�u�����X�V�iInsert�j����B<BR>
     * @@roseuid 413E62EE012A
     */
    public void saveNewSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvRegiKeyParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�쐬���t
        this.srvRegiKeyParams.setCreatedTimestamp(l_tsSystemTime);
        //�X�V���t
        this.srvRegiKeyParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.�T�[�r�X���p�L�[�s�I�u�W�F�N�g�̓��e�ŁA�T�[�r�X���p�L�[�e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.srvRegiKeyParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (remove�T�[�r�X���p�L�[)<BR>
     * �T�[�r�X���p�L�[�̏����f�[�^�x�[�X����폜����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA���Y���R�[�h���u�T�[�r�X���p�L�[�e�[�u���v���폜����B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h=this.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=this.�T�[�r�X�敪 and<BR>
     *   ���p�L�[��ʋ敪=this.���p�L�[��ʋ敪 and <BR>
     * �@@���p�L�[ID=this.���p�L�[ID<BR>
     * @@roseuid 413E62EE0139
     */
    public void removeSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and srv_use_key_type = ? ");
        l_sbWhere.append(" and srv_use_id = ? ");
        
        Object[] l_objWhere =
            {
                this.getInstitutionCode(),
                this.getSrvDiv(),
                this.getSrvUseKeyType(),
                new Long(this.getSrvUseKeyId())
            };
                          
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyRow.TYPE, l_sbWhere.toString(), l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
