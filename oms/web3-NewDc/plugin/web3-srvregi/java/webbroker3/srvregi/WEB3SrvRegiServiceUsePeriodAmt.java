head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUsePeriodAmt.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���ԗ���(WEB3SrvRegiServiceUsePeriodAmt.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���w�� (���u) �V�K�쐬
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
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p���ԗ���)<BR>
 * �T�[�r�X���p���ԗ����N���X<BR>                    
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUsePeriodAmt implements BusinessObject 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiServiceUsePeriodAmt.class);
    
    /**
     * (�T�[�r�X���p���ԗ����s)<BR>
     */
    private SrvRegiChargeParams srvUsePeriodAmtParams;

    /**
     * (�T�[�r�X���p���ԗ���)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐���<BR>�A
     * ����.�T�[�r�X���p���ԗ���Row��this.�T�[�r�X���p���ԗ����s�ɐݒ肷��B<BR>
     * @@param l_srvRegiChargeRow - (�T�[�r�X���p���ԗ���Row)<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3SrvRegiServiceUsePeriodAmt(SrvRegiChargeRow l_srvRegiChargeRow) throws WEB3BaseException 
    {
        this.srvUsePeriodAmtParams = new SrvRegiChargeParams(l_srvRegiChargeRow);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4133094B01AC
     */
    public Object getDataSourceObject() 
    {
        return this.srvUsePeriodAmtParams;
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�T�[�r�X���p���ԗ����s���R�s�[���āA�������e��<BR>
     * �ʃC���X�^���X���쐬����iclone�j�B<BR> 
     * �쐬�����R�s�[�����g��this.�T�[�r�X���p���ԗ����s�ɃZ�b�g����B<BR>
     * @@roseuid 4133094B01FA
     */
    public void createForUpdateParams() 
    {       
        SrvRegiChargeParams l_srvUsePeriodAmtParams = new SrvRegiChargeParams(this.srvUsePeriodAmtParams);
        this.srvUsePeriodAmtParams = l_srvUsePeriodAmtParams;        
    }
    
    /**
     * (createNew�ʔ�)<BR>
     * �T�[�r�X���p���ԗ����̐V�K�ʔԂ�ԋp����B<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p���ԗ����v�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * [���я�]<BR>
     * �@@�ʔԁ@@�~��<BR>
     * <BR>
     * 2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B<BR>
     * <BR>
     * 3) �������ʂ̌���>0���̏ꍇ�A�������ʂ̐擪�̗v�f�ƂȂ�<BR>
     * �@@�T�[�r�X���p���ԗ���Params.get�ʔ�( )+1��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return long
     * @@roseuid 412F0FAD00A3
     */
    public static long createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) �ȉ��̏����Łu�T�[�r�X���p���ԗ����v�e�[�u������������B
        //[��������]
        //�،���ЃR�[�h=����.�،���ЃR�[�h 
        //�T�[�r�X�敪=����.�T�[�r�X�敪 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv
            };
        List l_lisRecords = null;
        try
        {
            log.debug(SrvRegiChargeRow.TYPE + l_sbWhere.toString());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiChargeRow.TYPE,
                l_sbWhere.toString(),
                " consecutive_numbers desc",
                null,
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME);
        }
        
        long l_lngConsecutiveNumbers;
        //2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B
        if(l_lisRecords.size() == 0)
        {
            l_lngConsecutiveNumbers = 1;
        }
        else
        {
            //3) �������ʂ̌���>0���̏ꍇ�A�������ʂ̐擪�̗v�f�ƂȂ� �T�[�r�X���p���ԗ���Params.get�ʔ�( )+1��ԋp����B
            SrvRegiChargeRow l_srvRegiChargeRow = (SrvRegiChargeRow)l_lisRecords.get(0);
            l_lngConsecutiveNumbers = l_srvRegiChargeRow.getConsecutiveNumbers() + 1;
        }
                          
        log.exiting(STR_METHOD_NAME);
        return l_lngConsecutiveNumbers;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get�،���ЃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104846C0129
     */
    public String getInstitutionCode() 
    {
        String l_strInstitutionCode = this.srvUsePeriodAmtParams.getInstitutionCode();
        return l_strInstitutionCode;
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get�T�[�r�X�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104846C0148
     */
    public String getSrvDiv() 
    {
        String l_strSrvDiv = this.srvUsePeriodAmtParams.getSrvDiv();
        return l_strSrvDiv;
    }
    
    /**
     * (get�ʔ�)<BR>
     * �ʔԂ�Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get�ʔ�()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 4104846C0187
     */
    public long getConsecutiveNumbers() 
    {
        long l_lngConsecutiveNumbers = this.srvUsePeriodAmtParams.getConsecutiveNumbers();
        return l_lngConsecutiveNumbers;
    }
    
    /**
     * (set���p���ԋ敪)<BR>
     * ���p���ԋ敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���p���ԗ����s.set���p���ԋ敪()���R�[������B<BR>
     * [����]<BR>
     * �@@���p���ԋ敪=����.���p���ԋ敪<BR>
     * @@param l_strSrvUsePeriodDiv - (���p���ԋ敪)<BR>
     * @@roseuid 4104846C01B6
     */
    public void setSrvUsePeriodDiv(String l_strSrvUsePeriodDiv) 
    {
        this.srvUsePeriodAmtParams.setSrvUsePeriodDiv(l_strSrvUsePeriodDiv);
    }
    
    /**
     * (get���p���ԋ敪)<BR>
     * ���p���ԋ敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get���p���ԋ敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104846C0271
     */
    public String getSrvUsePeriodDiv() 
    {
        String l_strSrvUsePeriodDiv = this.srvUsePeriodAmtParams.getSrvUsePeriodDiv();
        return l_strSrvUsePeriodDiv;
    }
    
    /**
     * (set���p����)<BR>
     * ���p���Ԃ̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���p���ԗ����s.set���p����()���R�[������B<BR>
     * [����]<BR>
     * �@@���p����=����.���p����<BR>
     * Q&A WEB3-SRVREGI-A-DD-0017  ���p���ԁF���w�E�̒ʂ�Aint�ō\���܂���<BR>
     * @@param l_intSrvUsePeriod - (���p����)<BR>
     * @@roseuid 41048600037B
     */
    public void setSrvUsePeriod(int l_intSrvUsePeriod) 
    {
        this.srvUsePeriodAmtParams.setSrvUsePeriod(l_intSrvUsePeriod);
    }
    
    /**
     * (get���p����)<BR>
     * ���p���Ԃ�Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get���p����()�̖߂�l��Ԃ��B<BR>
     * @@return int
     * @@roseuid 41048601005E
     */
    public int getSrvUsePeriod() 
    {
        int l_intSrvUsePeriod = this.srvUsePeriodAmtParams.getSrvUsePeriod();
        return l_intSrvUsePeriod;
    }
    
    /**
     * (set���p����)<BR>
     * ���p�����̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���p���ԗ����s.set���p����()���R�[������B<BR>
     * [����]<BR>
     * �@@���p����=����.���p����<BR>
     * @@param l_lngUseAmt - (���p����)<BR>
     * @@roseuid 410486790177
     */
    public void setUseAmt(long l_lngUseAmt) 
    {
        this.srvUsePeriodAmtParams.setUseAmt(l_lngUseAmt);
    }
    
    /**
     * (get���p����)<BR>
     * ���p������Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get���p����()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 410486790233
     */
    public long getUseAmt() 
    {
        long l_lngUseAmt = this.srvUsePeriodAmtParams.getUseAmt();
        return l_lngUseAmt;
    }
    
    /**
     * (createNew�T�[�r�X���p���ԗ���)<BR>
     * �V�K�ɃT�[�r�X���p���ԗ����I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �T�[�r�X���p���ԗ���Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) �T�[�r�X���p���ԗ���Params.set�،���ЃR�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �T�[�r�X���p���ԗ���Params.set�T�[�r�X�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) this.�T�[�r�X���p���ԗ���Params.set�ʔ�()���R�[������B<BR>
     * [����]<BR>
     * �@@�ʔ�=�T�[�r�X���Ǘ�.createNew�ʔ�( )<BR>
     * �@@�@@[createNew�ʔԂɓn������]<BR>
     * �@@�@@�@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 5) �T�[�r�X���p���ԗ����̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�T�[�r�X���p���ԗ����I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p���ԗ���Row=���������T�[�r�X���p���ԗ���Params<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt
     * @@throws WEB3BaseException
     * @@roseuid 413E6311008D
     */
    public static WEB3SrvRegiServiceUsePeriodAmt createNewSrvUsePeriodAmt(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvUsePeriodAmt(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X���p���ԗ���Params�I�u�W�F�N�g�𐶐�����B
        SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
        
        //2) �T�[�r�X���p���ԗ���Params.set�،���ЃR�[�h()���R�[������B
        l_srvRegiChargeParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) �T�[�r�X���p���ԗ���Params.set�T�[�r�X�敪()���R�[������B
        l_srvRegiChargeParams.setSrvDiv(l_strSrvDiv);
        
        //4) this.�T�[�r�X���p���ԗ���Params.set�ʔ�()���R�[������B
        long l_lngConsecutiveNumbers;
        l_lngConsecutiveNumbers = createNewConsecutiveNumbers(l_strInstitutionCode, l_strSrvDiv);
        l_srvRegiChargeParams.setConsecutiveNumbers(l_lngConsecutiveNumbers);
        
        //5) �T�[�r�X���p���ԗ����̃R���X�g���N�^���R�[�����A���������T�[�r�X���p���ԗ����I�u�W�F�N�g��ԋp����B
        WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt = new WEB3SrvRegiServiceUsePeriodAmt(l_srvRegiChargeParams);

        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiServiceUsePeriodAmt;
    }
    
    /**
     * (save�T�[�r�X���p���ԗ���)<BR>
     * this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g��<BR>
     * �����f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�T�[�r�X���p���ԗ����s�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���p���ԗ����e�[�u�����X�V�iupdate�j����B<BR>
     * @@roseuid 413E631100AD
     */
    public void saveSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvUsePeriodAmt()";
        log.entering(STR_METHOD_NAME);
        
        //s1) this.�T�[�r�X���p���ԗ����s�Ɉȉ��̒l���Z�b�g����B
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvUsePeriodAmtParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�X�V���t
        this.srvUsePeriodAmtParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.srvUsePeriodAmtParams);//DataNetworkException,DataQueryException
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
     * (saveNew�T�[�r�X���p���ԗ���)<BR>
     * this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g��<BR>
     * �����f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���p���ԗ����e�[�u�����X�V�iInsert�j����B<BR>
     * @@roseuid 413E631100DC
     */
    public void saveNewSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvUsePeriodAmt()";
        log.entering(STR_METHOD_NAME);
        
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvUsePeriodAmtParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //�쐬���t
        this.srvUsePeriodAmtParams.setCreatedTimestamp(l_tsSystemTime);
        //�X�V���t
        this.srvUsePeriodAmtParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2)this.�T�[�r�X���p���ԗ����s�I�u�W�F�N�g�̓��e�ŁA�T�[�r�X���p���ԗ����e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.srvUsePeriodAmtParams);//DataNetworkException,DataQueryException
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
     * (remove�T�[�r�X���p���ԗ���)<BR>
     * �T�[�r�X���p���ԗ����̏����f�[�^�x�[�X����폜����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA���Y���R�[�h���T�[�r�X���p���ԗ����e�[�u�����폜����B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h=this.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=this.�T�[�r�X�敪 and<BR>
     * �@@�ʔ�=this.�ʔ�<BR>
     * @@roseuid 413E631100FB
     */
    public void removeSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvUsePeriodAmts()";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and consecutive_numbers = ? ");
        
        Object[] l_objWhere =
            {
                this.getInstitutionCode(),
                this.getSrvDiv(),
                new Long(this.getConsecutiveNumbers())
            };
                               
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doDeleteAllQuery(SrvRegiChargeRow.TYPE, l_sbWhere.toString(), l_objWhere);//DataNetworkException,DataQueryException
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
