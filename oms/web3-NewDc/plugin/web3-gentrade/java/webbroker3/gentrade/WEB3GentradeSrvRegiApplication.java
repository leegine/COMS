head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSrvRegiApplication.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �T�[�r�X�\���o�^(WEB3GentradeSrvRegiApplication.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 羐� (���u) �V�K�쐬
Revesion History : 2007/06/15 �h�C (���u)�y���ʁz�d�l�ύX�E���f��No.250
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �T�[�r�X�\���o�^�N���X
 */
public class WEB3GentradeSrvRegiApplication implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeSrvRegiApplication.class);

    /**
     * �T�[�r�X�\���o�^�s
     */
    private SrvRegiApplicationParams srvRegiApplicationParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �i�����s�̃C���X�^���X���擾����ۂɎg�p����j<BR>
     * <BR>
     * 1) ����.�T�[�r�X�\���o�^Row��this.�T�[�r�X�\���o�^�s�ɐݒ肷��B<BR>
     * @@param l_srvRegiApplicationParams - �T�[�r�X�\���o�^Params
     * @@roseuid 410491E8010A
     */
    public WEB3GentradeSrvRegiApplication(SrvRegiApplicationParams l_srvRegiApplicationParams)
    {
        final String STR_METHOD_NAME = "WEB3GentradeSrvRegiApplication(SrvRegiApplicationParams)";
        if (l_srvRegiApplicationParams == null)
        {
            log.error("�T�[�r�X�\���o�^�s = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X�\���o�^�s = null");
        }
        this.srvRegiApplicationParams = l_srvRegiApplicationParams;
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 4132F15B0064
     */
    public Object getDataSourceObject()
    {
        return this.srvRegiApplicationParams;
    }

    /**
     * (createForUpdateParams) <BR>
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�T�[�r�X�\���o�^�s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.�T�[�r�X�\���o�^�s�ɃZ�b�g����B <BR>
     * @@roseuid 4132F0200093
     */
    public void createForUpdateParams()
    {
        SrvRegiApplicationParams l_srvRegiApplicationParams = 
            new SrvRegiApplicationParams(this.srvRegiApplicationParams);
        this.srvRegiApplicationParams = l_srvRegiApplicationParams;
    }

    /**
     * (createNew�T�[�r�X�\���o�^) <BR>
     * �V�K�̃T�[�r�X�\���o�^�I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * <BR>
     * 1) �T�[�r�X�\���o�^Params�𐶐�����B<BR>
     * <BR>
     * 2) ���������T�[�r�X�\���o�^Params�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h=����.���X�R�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�����R�[�h=����.�����R�[�h<BR>
     * �@@�\���o�^ID=(*1)<BR>
     * �@@�L���敪="�L��"<BR>
     * �@@����敪="�ʏ�"<BR>
     * <BR>
     * (*1) this.createNew�\���o�^ID( )�̖߂�l<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h=����.���X�R�[�h<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * �@@�����R�[�h=����.�����R�[�h<BR>
     * <BR>
     * 3) this.�R���X�g���N�^���R�[�����A���������T�[�r�X�\���o�^<BR>
     *   �I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�\���o�^Row=���������T�[�r�X�\���o�^Params<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strSrvDiv - �T�[�r�X�敪
     * @@param l_strAccountCode - �����R�[�h
     * @@return WEB3GentradeSrvRegiApplication
     * @@throws WEB3BaseException
     * @@roseuid 4136A139005C
     */
    public static WEB3GentradeSrvRegiApplication createNewSrvRegiApplication(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strSrvDiv,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewSrvRegiApplication(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�\���o�^Params�𐶐�����
        SrvRegiApplicationParams l_srvRegiApplicationParams = 
            new SrvRegiApplicationParams();
        
        //createNew�\���o�^ID
        long l_lngRegistId =
            createNewRegistId(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode);
        
        //2) ���������T�[�r�X�\���o�^Params�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
        //�،���ЃR�[�h=����.�،���ЃR�[�h
        //���X�R�[�h=����.���X�R�[�h
        //�T�[�r�X�敪=����.�T�[�r�X�敪
        //�����R�[�h=����.�����R�[�h
        //�\���o�^ID=createNew�\���o�^ID( )�̖߂�l
        //�L���敪="�L��"
        //����敪="�ʏ�"
        l_srvRegiApplicationParams.setInstitutionCode(l_strInstitutionCode);
        l_srvRegiApplicationParams.setBranchCode(l_strBranchCode);
        l_srvRegiApplicationParams.setSrvDiv(l_strSrvDiv);
        l_srvRegiApplicationParams.setAccountCode(l_strAccountCode);
        l_srvRegiApplicationParams.setRegistId(l_lngRegistId);
        l_srvRegiApplicationParams.setEffectiveDiv(WEB3EffectiveDivDef.EFFECTIVE);
        l_srvRegiApplicationParams.setCancelDiv(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        
        //3) this.�R���X�g���N�^���R�[�����A
        // ���������T�[�r�X�\���o�^�I�u�W�F�N�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeSrvRegiApplication(l_srvRegiApplicationParams);
        
    }

    /**
     * (saveNew�T�[�r�X�\���o�^) <BR>
     * �T�[�r�X�\���o�^Params�̓��e���f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�쐬���t = <BR>
     *      GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t = <BR>
     *      GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X�\���o�^�e�[�u�����X�V�iInsert�j����B<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4136A13B0108
     */
    public void saveNewSrvRegiApplication()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveNewSrvRegiApplication()";
        log.entering(STR_METHOD_NAME);
        
        // 1) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
        // �쐬���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        //  �X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        this.srvRegiApplicationParams.setLastUpdatedTimestamp(l_tsSystemTime);
        this.srvRegiApplicationParams.setCreatedTimestamp(l_tsSystemTime);
        
        //2) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�̓��e�ŁA
        // �T�[�r�X�\���o�^�e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(this.srvRegiApplicationParams);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (save�T�[�r�X�\���o�^) <BR>
     * �T�[�r�X�\���o�^Params�̓��e���f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V���t = <BR>
     *     GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X�\���o�^�e�[�u�����X�V�iUpdate�j����B<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4136A13A00C9
     */
    public void saveSrvRegiApplication()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveSrvRegiApplication()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
        //�X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        this.srvRegiApplicationParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.�T�[�r�X�\���o�^�s�I�u�W�F�N�g�̓��e�ŁA
        // �T�[�r�X�\���o�^�e�[�u�����X�V�iUpdate�j����
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(this.srvRegiApplicationParams);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (createNew�\���o�^ID)<BR>
     * �����R�[�h�A�T�[�r�X�敪�P�ʂō̔Ԃ����<BR>
     * �V�K�s�p�̐\���o�^ID��ԋp����B<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * 1) �ȉ��̏����ŃT�[�r�X�\���o�^�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@���X�R�[�h=����.���X�R�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�����R�[�h=����.�����R�[�h<BR>
     * [���я�]<BR>
     * �@@�\���o�^ID�@@�~��<BR>
     * <BR>
     * 2) ��������=0���̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * 3) �������ʁ�0���̏ꍇ<BR>
     *  3-1) �������ʂ̐擪�̗v�f���擾����B<BR>
     * <BR>
     *  3-2) �ȉ��̒l��ԋp����B<BR>
     * �@@�@@�@@3-1)�Ŏ擾�����T�[�r�X�\���o�^Params.get�\���o�^ID( )+1<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strSrvDiv - �T�[�r�X�敪
     * @@param l_strAccountCode - �����R�[�h
     * @@return long
     * @@throws WEB3SystemLayerException
     * @@roseuid 412F29D601A0
     */
    public static long createNewRegistId(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strSrvDiv,
        String l_strAccountCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createNewRegistId(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) �ȉ��̏����ŃT�[�r�X�\���o�^�e�[�u������������B
        //�،���ЃR�[�h=����.�،���ЃR�[�h and
        //���X�R�[�h=����.���X�R�[�h and
        //�T�[�r�X�敪=����.�T�[�r�X�敪 and
        //�����R�[�h=����.�����R�[�h
        //[���я�] �F �\���o�^ID�@@�~��
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and account_code = ? ");
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode 
            };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_sbWhere.toString(),
                "regist_id desc",
                null,
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        long l_lngNewRegistId;
        //2) ��������=0���̏ꍇ�A0��ԋp����B
        if(l_lisRecords.size() == 0)
        {
            l_lngNewRegistId = 0;
        }
        else
        {
            SrvRegiApplicationRow l_srvRegiApplicationRow = 
                (SrvRegiApplicationRow)l_lisRecords.get(0);
            l_lngNewRegistId = l_srvRegiApplicationRow.getRegistId() + 1;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngNewRegistId;
       
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�،���ЃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 410491E801F4
     */
    public String getInstitutionCode()
    {
        return this.srvRegiApplicationParams.getInstitutionCode();
    }

    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get���X�R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 410493CD002F
     */
    public String getBranchCode()
    {
        return this.srvRegiApplicationParams.getBranchCode();
    }

    /**
     * (get�T�[�r�X�敪) <BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�T�[�r�X�敪()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 410491E80223
     */
    public String getSrvDiv()
    {
        return this.srvRegiApplicationParams.getSrvDiv();
    }

    /**
     * (get�����R�[�h) <BR>
     * �����R�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�����R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 410491E80252
     */
    public String getAccountCode()
    {
        return this.srvRegiApplicationParams.getAccountCode();
    }

    /**
     * (get�\���o�^ID) <BR>
     * �\���o�^ID��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�\���o�^ID( )�̖߂�l��Ԃ��B <BR>
     * @@return long
     * @@roseuid 4111925100C3
     */
    public long getRegistId()
    {
        return this.srvRegiApplicationParams.getRegistId();
    }

    /**
     * (set�K�p�J�n��) <BR>
     * �K�p�J�n���̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�K�p�J�n��()���R�[������B<BR>
     * [����]<BR>
     * �@@�K�p�J�n��=����.�K�p�J�n��<BR>
     * @@param l_tsAppliStartDate - �K�p�J�n��
     * @@roseuid 4104D9CD0177
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate)
    {
        this.srvRegiApplicationParams.setAppliStartDate(l_tsAppliStartDate);
    }

    /**
     * (get�K�p�J�n��) <BR>
     * �K�p�J�n����Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�K�p�J�n��()�̖߂�l��Ԃ��B<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4104943600DB
     */
    public Timestamp getAppliStartDate()
    {
        return this.srvRegiApplicationParams.getAppliStartDate();
    }

    /**
     * (set�K�p�I����) <BR>
     * �K�p�I�����̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�K�p�I����()���R�[������B <BR>
     * [����] <BR>
     * �@@�K�p�I����=����.�K�p�I���� <BR>
     * @@param l_tsAppliEndDate - �K�p�I����
     * @@roseuid 410491E802A0
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate)
    {
        this.srvRegiApplicationParams.setAppliEndDate(l_tsAppliEndDate);
    }

    /**
     * (get�K�p�I����) <BR>
     * �K�p�I������Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�K�p�I����()�̖߂�l��Ԃ��B <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410491E802CF
     */
    public Timestamp getAppliEndDate()
    {
        return this.srvRegiApplicationParams.getAppliEndDate();
    }

    /**
     * (set����ID) <BR>
     * ����ID�̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set����ID( )���R�[������B <BR>
     * [����] <BR>
     * �@@����ID=����.����ID <BR>
     * @@param l_orderId - ����ID
     * @@roseuid 4112306C0309
     */
    public void setOrderId(Long l_orderId)
    {
        this.srvRegiApplicationParams.setOrderId(l_orderId);
    }

    /**
     * (get����ID) <BR>
     * ����ID��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get����ID()�̖߂�l��Ԃ��B <BR>
     * @@return Long
     * @@roseuid 41073F3F03C1
     */
    public Long getOrderId()
    {
        if(this.srvRegiApplicationParams.getOrderIdIsNull())
        {
            return null;
        }
        else
        {   
            return new Long(this.srvRegiApplicationParams.getOrderId());
        }
    }

    /**
     * (set�\����)<BR>
     * �\�����̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�\����()���R�[������B<BR>
     * [����]<BR>
     * �@@�\����=����.�\����<BR>
     * @@param l_tsAppliDate - �\����
     * @@roseuid 410494DF0139
     */
    public void setAppliDate(Timestamp l_tsAppliDate)
    {
        this.srvRegiApplicationParams.setAppliDate(l_tsAppliDate);
    }

    /**
     * (get�\����) <BR>
     * �\������Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�\����()�̖߂�l��Ԃ��B <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410494DF0148
     */
    public Timestamp getAppliDate()
    {
        return this.srvRegiApplicationParams.getAppliDate();
    }

    /**
     * (set�o�^�敪) <BR>
     * �o�^�敪�̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�o�^�敪()���R�[������B <BR>
     * [����] <BR>
     * �@@�o�^�敪=����.�o�^�敪 <BR>
     * @@param l_strPaymentDiv - �o�^�敪  0�F�����@@1�F�L��
     * @@roseuid 410495CC02EE
     */
    public void setPaymentDiv(String l_strPaymentDiv)
    {
        this.srvRegiApplicationParams.setPaymentDiv(l_strPaymentDiv);
    }

    /**
     * (get�o�^�敪) <BR>
     * �o�^�敪��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�o�^�敪()�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 410495CC032D
     */
    public String getPaymentDiv()
    {
        return this.srvRegiApplicationParams.getPaymentDiv();
    }

    /**
     * (set�\�����I�敪) <BR>
     * �\�����I�敪�̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�\�����I�敪()���R�[������B <BR>
     * [����] <BR>
     * �@@�\�����I�敪=����.�\�����I�敪 <BR>
     * @@param l_strAppliLotDiv - �\�����I�敪
     * @@roseuid 410495380158
     */
    public void setAppliLotDiv(String l_strAppliLotDiv)
    {
        this.srvRegiApplicationParams.setAppliLotDiv(l_strAppliLotDiv);
    }

    /**
     * (get�\�����I�敪) <BR>
     * �\�����I�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�\�����I�敪()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 4104953801C5
     */
    public String getAppliLotDiv()
    {
        return this.srvRegiApplicationParams.getAppliLotDiv();
    }

    /**
     * (set�L���敪) <BR>
     * �L���敪�̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�L���敪()���R�[������B <BR>
     * [����] <BR>
     * �@@�L���敪=����.�L���敪 <BR>
     * @@param l_strEffectiveDiv - �L���敪
     * @@return java.lang.String
     * @@roseuid 411192870361
     */
    public void setEffectiveDiv(String l_strEffectiveDiv)
    {
        this.srvRegiApplicationParams.setEffectiveDiv(l_strEffectiveDiv);
    }

    /**
     * (get�L���敪) <BR>
     * �L���敪��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�L���敪()�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 4109E72F001B
     */
    public String getEffectiveDiv()
    {
        return this.srvRegiApplicationParams.getEffectiveDiv();
    }

    /**
     * (set����敪) <BR>
     * ����敪�̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set����敪()���R�[������B <BR>
     * [����] <BR>
     * �@@����敪=����.����敪 <BR>
     * @@param l_strCancelDiv - ����敪
     * @@return java.lang.String
     * @@roseuid 4111B0C002E4
     */
    public void setCancelDiv(String l_strCancelDiv)
    {
        this.srvRegiApplicationParams.setCancelDiv(l_strCancelDiv);
    }

    /**
     * (get����敪) <BR>
     * ����敪��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get����敪()�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 4111B0C002E6
     */
    public String getCancelDiv()
    {
        return this.srvRegiApplicationParams.getCancelDiv();
    }

    /**
     * (set���p����) <BR>
     * ���p�����̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set���p����()���R�[������B <BR>
     * [����] <BR>
     * �@@���p����=����.���p���� <BR>
     * @@param l_useAmt - ���p����
     * @@roseuid 41049697038A
     */
    public void setUseAmt(Double l_useAmt)
    {
        if(l_useAmt == null)
        {
            this.srvRegiApplicationParams.setUseAmt(null);
        }
        else
        {
            this.srvRegiApplicationParams.setUseAmt(l_useAmt.longValue());
        }
    }

    /**
     * (get���p����) <BR>
     * ���p������Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get���p����()�̖߂�l��Ԃ��B <BR>
     * @@return Double
     * @@roseuid 4104969703B9
     */
    public Double getUseAmt()
    {
        if(this.srvRegiApplicationParams.getUseAmtIsNull())
        {
            return null;
        }
        else
        {
            return new Double(this.srvRegiApplicationParams.getUseAmt());
        }
    }

    /**
     * (set�o����) <BR>
     * �o�����̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�o����()���R�[������B <BR>
     * [����] <BR>
     * �@@�o����=����.�o���� <BR>
     * @@param l_tsPaymentDate - �o����
     * @@roseuid 410496FB032D
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate)
    {
        this.srvRegiApplicationParams.setPaymentDate(l_tsPaymentDate);
    }

    /**
     * (get�o����) <BR>
     * �o������Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�o����()�̖߂�l��Ԃ��B <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410496FB033C
     */
    public Timestamp getPaymentDate()
    {
        return this.srvRegiApplicationParams.getPaymentDate();
    }

    /**
     * (set�������I���������)<BR>
     * �������I����������̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�������I���������( )���R�[������B<BR>
     * [����]<BR>
     * �@@�������I���������=����.�������I���������<BR>
     * @@param l_tsCancelLimitDate - �������I���������
     * @@roseuid 412AE7F3029B
     */
    public void setCancelLimitDate(Timestamp l_tsCancelLimitDate)
    {
        this.srvRegiApplicationParams.setCancelLimitDate(l_tsCancelLimitDate);
    }

    /**
     * (get�������I���������)<BR>
     * �������I�����������Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get�������I���������( )�̖߂�l��Ԃ��B<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 412AE7F30366
     */
    public Timestamp getCancelLimitDate()
    {
        return this.srvRegiApplicationParams.getCancelLimitDate();
    }

    /**
     * (get�ŏI�X�V��) <BR>
     * �X�V���t��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�X�V���t()�̖߂�l��Ԃ��B <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4107A0510139
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        return this.srvRegiApplicationParams.getLastUpdatedTimestamp();
    }

    /**
     * (get�ŏI�X�V��) <BR>
     * �X�V�҃R�[�h��Ԃ��B <BR>
     *  <BR>
     * this.�T�[�r�X�\���o�^�s.get�X�V�҃R�[�h()�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 4107A057038A
     */
    public String getLastUpdater()
    {
        return this.srvRegiApplicationParams.getLastUpdater();
    }
    
    /**
     * (set�ŏI�X�V��) <BR>
     * �ŏI�X�V�҂̐ݒ���s���B <BR>
     *  <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set�X�V�҃R�[�h()���R�[������B<BR>
     * [����] <BR>
     * �X�V�҃R�[�h=����.�ŏI�X�V�� <BR>
     * <BR>
     * @@param l_strLastUpdater - (�ŏI�X�V��)
     * @@return java.lang.String
     * @@roseuid 4107A057038A
     */
    public void setLastUpdater(String l_strLastUpdater)
    {
        this.srvRegiApplicationParams.setLastUpdater(l_strLastUpdater);
    }

    /**
     * (set���������\���敪) <BR>
     * ���������\���敪�̐ݒ���s���B <BR>
     * <BR>
     * 1) this.�T�[�r�X�\���o�^�s.set���������\���敪()���R�[������B <BR>
     * [����] <BR>
     * �@@���������\���敪=����.���������\���敪 <BR>
     * <BR>
     * @@param l_strFreeSrvDiv - ���������\���敪
     */
    public void setFreeSrvDiv(String l_strFreeSrvDiv)
    {
        this.srvRegiApplicationParams.setFreeSrvDiv(l_strFreeSrvDiv);
    }

    /**
     * (get���������\���敪) <BR>
     * ���������\���敪��Ԃ��B <BR>
     * <BR>
     * this.�T�[�r�X�\���o�^�s.get���������\���敪()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     */
    public String getFreeSrvDiv()
    {
        return this.srvRegiApplicationParams.getFreeSrvDiv();
    }
}
@
