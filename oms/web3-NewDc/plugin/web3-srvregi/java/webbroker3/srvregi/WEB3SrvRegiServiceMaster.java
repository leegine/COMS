head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X�}�X�^�[(WEB3SrvRegiServiceMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �s�p (���u) �V�K�쐬
Revesion History : 2009/04/24 �Ԑi (���u) ���f��407�A413�A414
Revesion History : 2009/05/20 �đo�g (���u) ���f��420,421
*/

package webbroker3.srvregi;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.srvregi.data.SrvRegiCommCondDao;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiConsDocRow;
import webbroker3.srvregi.data.SrvRegiKeyDao;
import webbroker3.srvregi.data.SrvRegiKeyRow;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X�}�X�^�[)<BR>
 * �T�[�r�X�}�X�^�[�G���e�B�e�B�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3SrvRegiServiceMaster implements BusinessObject 
{
    
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceMaster.class);
    

    /**
     * (�T�[�r�X�}�X�^�[�s)<BR>
     */
    private SrvRegiMasterParams srvMasterParams;
    
    /**
     * (�T�[�r�X�}�X�^�[)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐����A<BR>
     * ����.�T�[�r�X�}�X�^�[Row��this.�T�[�r�X�}�X�^�[�s�ɐݒ肷��B<BR>
     * @@param l_srvMasterRow - (�T�[�r�X�}�X�^�[Row)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412F069E02D6
     */
    protected WEB3SrvRegiServiceMaster(SrvRegiMasterRow l_srvMasterRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiServiceMaster(SrvRegiMasterRow) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams = new SrvRegiMasterParams(l_srvMasterRow);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�T�[�r�X�}�X�^�[�s���R�s�[���āA�������e��<BR>
     * �ʃC���X�^���X���쐬����iclone�j�B<BR> 
     * �쐬�����R�s�[�����g��this.�T�[�r�X�}�X�^�[�s�ɃZ�b�g����B<BR>
     * @@roseuid 413308C70100
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiMasterParams l_srvMasterParams = new SrvRegiMasterParams(this.srvMasterParams);
        this.srvMasterParams = l_srvMasterParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR> 
     * this.�T�[�r�X�}�X�^�[�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 413308C700D1
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�،���ЃR�[�h( )�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40F6797B03C2
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getInstitutionCode();
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�T�[�r�X�敪( )�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40F6799400B4
     */
    public String getSrvDiv() 
    {
        final String STR_METHOD_NAME = " getSrvDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvDiv();
    }
    
    /**
     * (get�T�[�r�X����)<BR>
     * �T�[�r�X���̂�Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�T�[�r�X����( )�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412EF0870343
     */
    public String getSrvName() 
    {
        final String STR_METHOD_NAME = " getSrvName() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvName();
    }
    
    /**
     * (set�T�[�r�X����)<BR>
     * �T�[�r�X���̂̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.set�T�[�r�X����( )���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X����=����.�T�[�r�X����<BR>
     * @@param l_strSrvName - (�T�[�r�X����)<BR>
     * @@roseuid 412EF0870314
     */
    public void setSrvName(String l_strSrvName) 
    {
        final String STR_METHOD_NAME = " setSrvName(String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams.setSrvName(l_strSrvName);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�X�e�[�^�X)<BR>
     * �X�e�[�^�X��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�X�e�[�^�X( )�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104A8F7010A
     */
    public String getStatus() 
    {
        final String STR_METHOD_NAME = " getStatus() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvStatus();
    }
    
    /**
     * (set�X�e�[�^�X)<BR>
     * �X�e�[�^�X�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.set�X�e�[�^�X( )���R�[������B<BR>
     * [����]<BR>
     * �@@�X�e�[�^�X=����.�X�e�[�^�X<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * @@roseuid 4104A8F700EA
     */
    public void setStatus(String l_strStatus) 
    {
        final String STR_METHOD_NAME = " setStatus(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvMasterParams.setSrvStatus(l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�T�[�r�X���pURL)<BR>
     * �T�[�r�X���pURL��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�T�[�r�X���pURL( )�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104A94E00BC
     */
    public String getSrvUrl() 
    {
        final String STR_METHOD_NAME = " getSrvUrl() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvUrl();
    }
    
    /**
     * (set�T�[�r�X���pURL)<BR>
     * �T�[�r�X���pURL�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.set�T�[�r�X���pURL( )���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���pURL=����.�T�[�r�X���pURL<BR>
     * @@param l_strSrvUrl - (�T�[�r�X���pURL)<BR>
     * @@roseuid 4104A94E00AC
     */
    public void setSrvUrl(String l_strSrvUrl) 
    {
        final String STR_METHOD_NAME = " setSrvUrl(String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams.setSrvUrl(l_strSrvUrl);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�\���敪)<BR>
     * �\���敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.set�\���敪( )���R�[������B<BR>
     * [����]<BR>
     * �@@�\���敪=����.�\���敪<BR>
     * @@param l_strOfferingDiv - (�\���敪)<BR>
     * @@roseuid 412081C6025E
     */
    public void setOfferingDiv(String l_strOfferingDiv) 
    {
        final String STR_METHOD_NAME = " setOfferingDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvMasterParams.setOfferingDiv(l_strOfferingDiv);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�T�[�r�X���I���)<BR>
     * ����.�ʔԂ���T�[�r�X�ɕR�t���T�[�r�X���I���I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     * �@@�ʔ�=����.�ʔ�<BR>
     * <BR>
     * 2) ��������=0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * 3) �擾�����u�T�[�r�X���I���Params�v�������ɁA�T�[�r�X���I����<BR>
     * �@@�R���X�g���N�^���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=�擾�����T�[�r�X���I���Params<BR>
     * <BR>
     * 4) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@���������T�[�r�X���I���.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 5) ���������T�[�r�X���I����ԋp����B<BR>
     * @@param l_lngConsecutiveNumbers - (�ʔ�)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@roseuid 412EF0B20288
     */
    public WEB3SrvRegiServiceLotInfo getSrvLotInfo(long l_lngConsecutiveNumbers, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfo(long, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiServiceLotInfo l_srvLotInfo = null;
        
        
        //according to the QA of WEB3-SEVREGI-1-CD-0013
        try
        {
            //1) �ȉ��̏����Łu�T�[�r�X���I���e�[�u���v����������B
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                new Long(l_lngConsecutiveNumbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE, 
                    l_strWhere,
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisSrvLotInfoRowList = 
                    l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException, DataQueryException
            }
            
            //2) ��������=0���̏ꍇ�Anull��ԋp����B
            if (l_lisSrvLotInfoRowList == null || l_lisSrvLotInfoRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisSrvLotInfoRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) �擾�����u�T�[�r�X���I���Params�v�������ɁA�T�[�r�X���I����
            l_srvLotInfo = new 
                WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(0));
            
            //4) ����.is�s���b�N=true�̏ꍇ
            if (l_blnIsRowLock)
            {
                 log.debug("����.is�s���b�N=true�̏ꍇ");
                 
                 l_srvLotInfo.createForUpdateParams();
            }
            
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);

        //5) ���������T�[�r�X���I����ԋp����B
        return l_srvLotInfo;
    }
    
    /**
     * (get�T�[�r�X���I���ꗗ)<BR>
     * ���T�[�r�X�̃T�[�r�X���I���̔z���Ԃ��B<BR>
     * <BR>
     * 1) �T�[�r�X���I���e�[�u�����������A�T�[�r�X���I���Params��<BR>
     * List���擾����B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     * �@@�K�p�I����>=���ݓ���(*)�̓��t����<BR>
     * [���ёւ�]<BR>
     * �@@�K�p�J�n���ŏ���<BR>
     * <BR>
     * (*) GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) �擾����List����T�[�r�X���I���Params���Ƃ肾���A�T�[�r�X<BR>
     * ���I���I�u�W�F�N�g��<BR>
     * �@@��������B���������T�[�r�X���I���I�u�W�F�N�g��z��ɐݒ肵�ĕԂ��B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=�擾�����T�[�r�X���I���Params<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo[ ]
     * @@roseuid 412EF067016E
     */
    public WEB3SrvRegiServiceLotInfo[] getSrvLotInfoList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfoList() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = null;
        
        try
        {
            //1) �T�[�r�X���I���e�[�u�����������A�T�[�r�X���I���Params��
            String l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp())};
               
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, 
                l_strWhere, 
                " appli_start_date asc ", 
                "",
                l_obj);//DataNetworkException, DataQueryException
                
            if (l_lisSrvLotInfoRowList != null)
            {
                //2) �擾����List����T�[�r�X���I���Params���Ƃ肾���A�T�[�r�X
                int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();
            
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[l_intSrvLotInfoRowCnt];
            
                for (int i = 0; i < l_intSrvLotInfoRowCnt; i++)
                {
                    l_srvLotInfos[i] =  new 
                        WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(i));
    
                }
            }
            else
            {
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvLotInfos;
    }
    
    /**
     * (get�T�[�r�X���p���ԗ���)<BR>
     * ����.�ʔԂ���T�[�r�X�ɕR�t���T�[�r�X���p���ԗ����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p���ԗ����e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     * �@@�ʔ�=����.�ʔ�<BR>
     * <BR>
     * 2) ��������=0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * 3) �擾�����u�T�[�r�X���p���ԗ���Params�v�������ɁA�T�[�r�X<BR>
     * ���p���ԗ�����<BR>
     * �@@�R���X�g���N�^���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p���ԗ���Row=�擾�����T�[�r�X���p���ԗ���Params<BR>
     * <BR>
     * 4) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@���������T�[�r�X���p���ԗ���.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 5) ���������T�[�r�X���p���ԗ�����ԋp����B<BR>
     * @@param l_lngConsecutive_Numbers - (�ʔ�)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt
     * @@roseuid 412EF0B20249
     */
    public WEB3SrvRegiServiceUsePeriodAmt getSrvUseTermAmt(long l_lngConsecutive_Numbers, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseTermAmt(long, boolean)  ";
        log.entering(STR_METHOD_NAME);        
        
        WEB3SrvRegiServiceUsePeriodAmt l_usePeriodAmt = null;
        
        try
        {
            //according to the QA of WEB3-SEVREGI-1-DD-0013
            //1) �ȉ��̏����Łu�T�[�r�X���p���ԗ����e�[�u���v����������B             
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                new Long(l_lngConsecutive_Numbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
                        
            List l_lisUsePeriodAmtRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiChargeRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiChargeRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException ,DataQueryException
            }
                
            int l_intUsePeriodAmtRowCnt = l_lisUsePeriodAmtRowList.size();
                
            //2) ��������=0���̏ꍇ�Anull��ԋp����B
            if (l_lisUsePeriodAmtRowList ==null || l_intUsePeriodAmtRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisUsePeriodAmtRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) �擾�����u�T�[�r�X���p���ԗ���Params�v�������ɁA�T�[�r�X���p���ԗ���
                l_usePeriodAmt = new 
                    WEB3SrvRegiServiceUsePeriodAmt((SrvRegiChargeRow)l_lisUsePeriodAmtRowList.get(0));
            
            //4) ����.is�s���b�N=true�̏ꍇ
            if (l_blnIsRowLock)
            {
                log.debug("now isRowLock = true!");
                
                l_usePeriodAmt.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) ���������T�[�r�X���p���ԗ�����ԋp����B
        return l_usePeriodAmt;        
    }
    
    /**
     * (get�T�[�r�X���p���ԗ����ꗗ)<BR>
     * ���T�[�r�X�̃T�[�r�X���p���ԗ����̔z���Ԃ��B<BR>
     * <BR>
     * 1) �T�[�r�X���p���ԗ����e�[�u�����������A�T�[�r�X���p<BR>
     * ���ԗ���Params��List���擾����B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( )<BR>
     * [���ёւ�]<BR>
     * �@@�ʔԂŏ���<BR>
     * <BR>
     * 2) �擾����List����T�[�r�X���p���ԗ���Params���Ƃ肾���A�T�[�r�X<BR>
     * ���p���ԗ����I�u�W�F�N�g��<BR>
     * �@@��������B���������T�[�r�X���p���ԗ����I�u�W�F�N�g��z��ɐݒ肵�ĕԂ��B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p���ԗ���Row=�擾�����T�[�r�X���p���ԗ���Params<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt[ ]
     * @@roseuid 412EF0670101
     */
    public WEB3SrvRegiServiceUsePeriodAmt[] getSrvUseTermAmtList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseTermAmtList() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3SrvRegiServiceUsePeriodAmt[] l_usePeriodAmts = null;
        
        try
        {
            //1) �T�[�r�X���p���ԗ����e�[�u�����������A�T�[�r�X���p  
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
               
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiChargeRow.TYPE, 
                l_strWhere, 
                " consecutive_numbers asc ",
                "", 
                l_obj);//DataNetworkException, DataQueryException
                
            if (l_lisUsePeriodAmtRowList != null)
            {
                //2) �擾����List����T�[�r�X���p���ԗ���Params���Ƃ肾���A�T�[�r�X
                int l_intUsePeriodAmtRowCnt = l_lisUsePeriodAmtRowList.size();
            
                l_usePeriodAmts = new WEB3SrvRegiServiceUsePeriodAmt[l_intUsePeriodAmtRowCnt];
            
                for (int i = 0; i < l_intUsePeriodAmtRowCnt; i++)
                {
                    l_usePeriodAmts[i] =  new 
                        WEB3SrvRegiServiceUsePeriodAmt((SrvRegiChargeRow)l_lisUsePeriodAmtRowList.get(i));
        
                }
            }
            else
            {
                l_usePeriodAmts = new WEB3SrvRegiServiceUsePeriodAmt[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_usePeriodAmts;
    }
    
    /**
     * (get�T�[�r�X���p�L�[)<BR>
     * ����.���p�L�[ID����T�[�r�X�ɕR�t���T�[�r�X���p�L�[�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and <BR>
     * �@@���p�L�[��ʋ敪=����.���p�L�[��ʋ敪 and <BR>
     * �@@���p�L�[ID=����.get���p�L�[ID( )<BR>
     * <BR>
     * 2) ��������=0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * 3) �擾�����u�T�[�r�X���p�L�[Params�v�������ɁA�T�[�r�X���p�L�[��<BR>
     * �@@�R���X�g���N�^���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���p�L�[Row=�擾�����T�[�r�X���p�L�[Params<BR>
     * <BR>
     * 4) ����.is�s���b�N=true�̏ꍇ<BR>
     * �@@���������T�[�r�X���p�L�[.createForUpdateParams( )���R�[������B<BR>
     * <BR>
     * 5) ���������T�[�r�X���p�L�[��ԋp����B<BR>
     * @@param l_strSrvUseKeyTypeDiv - (���p�L�[��ʋ敪)<BR>
     * @@param l_lngUseKeyId - (���p�L�[ID)<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUseKey
     * @@roseuid 4132BA8F02F4
     */
    public WEB3SrvRegiServiceUseKey getSrvUseKey(String l_strSrvUseKeyTypeDiv, long l_lngUseKeyId, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseKey(String, long, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiServiceUseKey l_serviceUseKey = null;
        
        try
        {
            //according to the QA of WEB3-SEVREGI-1-DD-0013
            //1) �ȉ��̏����Łu�T�[�r�X���p�L�[�e�[�u���v����������B
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? and srv_use_id = ? ";
               
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                l_strSrvUseKeyTypeDiv, 
                new Long(l_lngUseKeyId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisServiceUseKeyRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisServiceUseKeyRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiKeyRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisServiceUseKeyRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiKeyRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException, DataQueryException
            }
                
            //2) ��������=0���̏ꍇ�Anull��ԋp����B
            if (l_lisServiceUseKeyRowList == null || l_lisServiceUseKeyRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
               
            if (l_lisServiceUseKeyRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) �擾�����u�T�[�r�X���p�L�[Params�v�������ɁA�T�[�r�X���p�L�[��
            l_serviceUseKey = new WEB3SrvRegiServiceUseKey((SrvRegiKeyRow)l_lisServiceUseKeyRowList.get(0));
            
            //4) ����.is�s���b�N=true�̏ꍇ
            if (l_blnIsRowLock)
            {
                log.debug("now isRowLock = true!");
                l_serviceUseKey.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) ���������T�[�r�X���p�L�[��ԋp����B
        return l_serviceUseKey;
    }    
    
    /**
     * (get�m�F���[�����)<BR>
     * ���T�[�r�X�̊m�F���[������Ԃ��B<BR>
     * <BR>
     * 1) ���[�����Ǘ�.get���[��( )���R�[�����A���[���I�u�W�F�N�g���擾���ĕԂ��B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( )<BR>
     * �@@���M���[���敪="�T�[�r�X���p�i�m�F���[���j"<BR>
     * �@@����ID=this.get�T�[�r�X�敪( )<BR>
     * @@return WEB3GentradeMail
     * @@roseuid 412EF0B201DC
     */
    public WEB3GentradeMailInfo getConfirmMailInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmMailInfo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return WEB3AdminMailInfoManager.getMail(
            this.getInstitutionCode(), 
            WEB3SendmailDivDef.SRVREGI_CONFIRM_MAIL, 
            this.getSrvDiv());
    }
    
    /**
     * (get�_��������[�����)<BR>
     * ���T�[�r�X�̌_��������[������Ԃ��B<BR>
     * <BR>
     * 1) ���[�����Ǘ�.get���[��( )���R�[�����A���[���I�u�W�F�N�g���擾���ĕԂ��B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( )<BR>
     * �@@���M���[���敪="�T�[�r�X���p�i�_��������[���j"<BR>
     * �@@����ID=this.get�T�[�r�X�敪( )<BR>
     * @@return WEB3GentradeMail
     * @@roseuid 412EF0B2021A
     */
    public WEB3GentradeMailInfo getEndMaiDivInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEndMaiDivInfo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return WEB3AdminMailInfoManager.getMail(
            this.getInstitutionCode(), 
            WEB3SendmailDivDef.SRVREGI_TERM_MAIL, 
            this.getSrvDiv());
    }
    
    /**
     * (get�\���v�T�[�r�X)<BR>
     * �T�[�r�X�ɕR�t���\���v�T�[�r�X�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�\���v�T�[�r�X�e�[�u���v����������B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     * <BR>
     * 2) ��������=0���̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * 3) �擾�����\���v�T�[�r�XParams�������ɁA�\���v�T�[�r�X��<BR>
     * �@@�R���X�g���N�^���R�[�����A�擾�����I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�\���v�T�[�r�XRow=�擾�����\���v�T�[�r�XParams<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService
     * @@roseuid 412EF6F1018E
     */
    public WEB3SrvRegiApplicationRequiredService getAppliRequiredSrv(boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliRequiredSrv(boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplicationRequiredService l_applicationRequiredService = null;
        
        try
        {
            //1)  �ȉ��̏����Łu�\���v�T�[�r�X�e�[�u���v����������B
            //according to the QA of WEB3-SEVREGI-1-DD-008
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisApplicationRequiredServiceRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisApplicationRequiredServiceRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiSetupRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisApplicationRequiredServiceRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiSetupRow.TYPE, 
                    l_strWhere, l_obj);//DataNetworkException, DataQueryException
            }
                
            //2) ��������=0���̏ꍇ�Anull��ԋp����B
            if (l_lisApplicationRequiredServiceRowList == null || 
                l_lisApplicationRequiredServiceRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisApplicationRequiredServiceRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //3) �擾�����\���v�T�[�r�XParams�������ɁA�\���v�T�[�r�X��
            l_applicationRequiredService = new WEB3SrvRegiApplicationRequiredService(
                (SrvRegiSetupRow)l_lisApplicationRequiredServiceRowList.get(0));
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_applicationRequiredService;
    }
    
    /**
     * (get���ӏ�����)<BR>
     * ���ӏ��I�u�W�F�N�g���擾���A�ԋp����B<BR>
     * <BR>
     * 1) ���ӏ������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) ���ӏ������e�[�u�������������ӏ�����Params��List���擾����B<BR>
     * �i����.is�s���b�N��true�̏ꍇ�Aselect for update�Ō������s���B)<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( )<BR>
     * [���ёւ�]<BR>
     * �@@�s�ԍ��ŏ���<BR>
     * <BR>
     * 3) List�̗v�f����0�̏ꍇ�́A�����������ӏ�����<BR>
     * �I�u�W�F�N�g.������null��ݒ肷��B<BR>
     * <BR>
     * 4) List�̗v�f�� �� 0 �̏ꍇ�A�ȉ��̏������s���B<BR>
     *  4-1) List���珇�Ԃɓ��ӏ�����Params�����o���B<BR>
     *  4-2) ���o�������ӏ�����Params.get����( )�̖߂�l��A������<BR>
     * �@@�@@�P�̕�����ɂ��A�����������ӏ������I�u�W�F�N�g.�����ɐݒ肷��B<BR>
     * <BR>
     * 5) �����������ӏ������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * true:�s���b�N���s���@@false:�s���b�N���s��Ȃ�<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiConsDoc
     * @@roseuid 412EF5980065
     */
    public WEB3SrvRegiConsDoc getConsDoc(boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConsDoc(boolean) ";
        log.entering(STR_METHOD_NAME);
        
        //1) ���ӏ������I�u�W�F�N�g�𐶐�����B
        WEB3SrvRegiConsDoc l_consDoc = new  WEB3SrvRegiConsDoc();
        
        try
        {
            //2) ���ӏ������e�[�u�������������ӏ�����Params��List���擾����B
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
           
            List l_lisConsDocRowList = null;
            
            SrvRegiConsDocRow l_consDocRow = null;
            
            String l_strCons = "";
            
            if (l_blnIsRowLock)
            {
                l_lisConsDocRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiConsDocRow.TYPE, 
                    l_strWhere, 
                    " line_number asc ", 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisConsDocRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiConsDocRow.TYPE,  
                    l_strWhere, 
                    " line_number asc ",
                    "", 
                    l_obj);//DataNetworkException, DataQueryException
            } 
                
            //3) List�̗v�f����0�̏ꍇ�́A�����������ӏ�����
            if (l_lisConsDocRowList == null || l_lisConsDocRowList.size() == 0)
            {
                l_consDoc.setCons(null);
            }
            else                 
            //4) List�̗v�f�� �� 0 �̏ꍇ�A�ȉ��̏������s���B
            {
                int l_intConsDocRowCnt = l_lisConsDocRowList.size();
                
                for (int i = 0; i < l_intConsDocRowCnt; i++)
                {
                    //4-1) List���珇�Ԃɓ��ӏ�����Params�����o���B
                    l_consDocRow = (SrvRegiConsDocRow)l_lisConsDocRowList.get(i);
                    
                    //4-2) ���o�������ӏ�����Params.get����( )�̖߂�l��A������
                    l_strCons += l_consDocRow.getLineValue();
                }
                l_consDoc.setCons(l_strCons);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) �����������ӏ������I�u�W�F�N�g��ԋp����
        return l_consDoc;
    }
    
    /** 
     * (get�萔�������ꗗ)<BR>
     * ���Y�T�[�r�X�ɕt������Ă���T�[�r�X���p�萔�������̈ꗗ��ԋp����B<BR> 
     * <BR>
     * �|�ȉ��̌��������Łu�T�[�r�X���p�萔�������e�[�u���v����������B<BR> 
     * [��������] <BR>
�@@   * �،���ЃR�[�h=this.get�،���ЃR�[�h( ) <BR>
�@@   * �T�[�r�X�敪=this.get�T�[�r�X�敪( ) <BR>
     * <BR>
     * �|�������ʂ��u�T�[�r�X���p�萔�������v�̃��X�g�ɂ��ĕԋp����B<BR>
     * @@return List
     */
    public List getCommCondList() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCommCondList()";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCommCondRows = null;
        try
        {
            //�ȉ��̌��������Łu�T�[�r�X���p�萔�������e�[�u���v����������B
            String l_strWhere = " institution_code = ? and srv_div = ?";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_lisCommCondRows = l_queryProcessor.doFindAllQuery(
                SrvRegiCommCondRow.TYPE, 
                l_strWhere, l_obj);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisCommCondRows;
    }

    /** 
     * (get�萔������)<BR>
     * �w�肳�ꂽ�T�[�r�X���p�萔��������ԋp����B<BR> 
     * <BR>
     * �|�ȉ��̌��������Łu�T�[�r�X���p�萔�������e�[�u���v����������B<BR> 
     * [��������] <BR>
�@@   * �،���ЃR�[�h=this.get�،���ЃR�[�h( ) <BR>
�@@   * �T�[�r�X�敪=this.get�T�[�r�X�敪( ) <BR>
�@@   * ������t���i=����.������t���i <BR>
     * <BR>
     * �|�������ʂ��u�T�[�r�X���p�萔�������v�I�u�W�F�N�g�ɂ��ĕԋp����B<BR>
     * @@param l_strOrderAccProduct - (������t���i)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiCommCond
     */
    public WEB3SrvRegiCommCond getCommCond(String l_strOrderAccProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCommCond(String) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCommCond l_commCondition = null;
        
        try
        {
            SrvRegiCommCondRow l_srvRegiCommCondRow = 
                SrvRegiCommCondDao.findRowByInstitutionCodeSrvDivOrderAccProduct(
                    this.getInstitutionCode(),
                    this.getSrvDiv(),
                    l_strOrderAccProduct);//DataNetworkException, DataQueryException
            if (l_srvRegiCommCondRow != null)
            {
                l_commCondition = new WEB3SrvRegiCommCond(this.getInstitutionCode(), this.getSrvDiv(), l_strOrderAccProduct);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_commCondition;
    }
    
    /**
     * (is�\���\)<BR>
     * ���Y�T�[�r�X���\���\���ǂ�����ԋp����B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.get�X�e�[�^�X()�̖߂�l="�񋟒�"�̏ꍇ<BR>
     * �@@true��ԋp����B����ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 412EF0C2017E
     */
    public boolean isAppliPossible() 
    {
        final String STR_METHOD_NAME = " isAppliPossible() ";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3SrvStatusDef.PROVIDING.equals(this.srvMasterParams.getSrvStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is�\���v)<BR>
     * ���T�[�r�X���\���K�v���ۂ���Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X�}�X�^�[�s.get�\���敪()�̖߂�l��"�v"�̏ꍇ�� true ���A<BR>
     * �����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40F679A001AE
     */
    public boolean isAppliRequired() 
    {
        final String STR_METHOD_NAME = " isAppliRequired() ";
        log.entering(STR_METHOD_NAME);

        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.srvMasterParams.getOfferingDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is�񋟒�)<BR>
     * ���Y�T�[�r�X���񋟒����ǂ�����ԋp����B<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s.get�X�e�[�^�X()�̖߂�l="��~��"�ȊO�̏ꍇ<BR>
     * �@@true��ԋp����B����ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 412EF0C901AD
     */
    public boolean isProviding() 
    {
        final String STR_METHOD_NAME = " isProviding() ";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3SrvStatusDef.STOP.equals(this.srvMasterParams.getSrvStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (get��QURL)<BR>
     * ���Y�T�[�r�X�ɕR�t���h��QURL�h��ԋp����B<BR> 
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR> 
     * [��������] <BR>
     *�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     *�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@���p�L�[��ʋ敪="��QURL" and <BR>
     *�@@�T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * <BR>
     * 2) �߂�l�̐ݒ� <BR>
     * 2-1) 1)�̌�������=1���̏ꍇ <BR>
     *  1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@ �߂�l��ԋp����B<BR> 
     * <BR>
     * 2-2) ) 1)�̌�������=0���̏ꍇ�Anull��ԋp����B<BR>
     * @@return String
     */
    public String getUrl2() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUrl2() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.URL2,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�n�b�V���v�Z�����敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h�n�b�V���v�Z�����敪�h��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������] <BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@ ���p�L�[��ʋ敪="�n�b�V���v�Z�����敪" and <BR> 
     *�@@ �T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * 2) 1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@�߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getHashCalHowToDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashCalHowToDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�n�b�V���v�Z�菇�敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h�n�b�V���v�Z�菇�敪�h��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������] <BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@ ���p�L�[��ʋ敪="�n�b�V���v�Z�菇�敪" and <BR> 
     *�@@ �T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * 2) 1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@�߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getHashCalOrderDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashCalOrderDiv() ";
        log.entering(STR_METHOD_NAME);
    
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
        
            log.exiting(STR_METHOD_NAME);
        
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
         
            log.exiting(STR_METHOD_NAME);
        
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���M���@@�敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h���M���@@�敪�h��ԋp����B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������] <BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@ ���p�L�[��ʋ敪="���M���@@�敪" and <BR> 
     *�@@ �T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * 2) 1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@�߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getSendHowToDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSendHowToDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }   
    
    /**
     * (get�Í����ڋq�R�[�h�敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h�Í����ڋq�R�[�h�敪�h�𔻒肷��B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������] <BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@ ���p�L�[��ʋ敪="�Í����ڋq�R�[�h�敪" and <BR> 
     *�@@ �T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * 2) 1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@�߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getCryptAccountCodeDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCryptAccountCodeDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���M�p�����[�^�敪)<BR>
     * �Y�T�[�r�X�ɕR�t���h���M�p�����[�^�敪�h�𔻒肷��B<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������] <BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@ ���p�L�[��ʋ敪="���M�p�����[�^�敪" and <BR> 
     *�@@ �T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR> 
     * 2) 1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� <BR>
     *�@@�߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getSendParamDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSendParamDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }    
    
    /**
     * (get�n�b�V���l�ꗗ) <BR>
     *���Y�T�[�r�X�ɕR�t���n�b�V���l�̈ꗗ��ԋp����B<BR> 
     * <BR>
     *1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR> 
     *[��������] <BR>
     *�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     *�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@���p�L�[��ʋ敪="�n�b�V���l" <BR>
     * [���ёւ�]<BR>
     * �@@�T�[�r�X���p�L�[ID�ŏ���<BR>
     * <BR>
     *2) 1)�̌������ʂ��T�[�r�X���p�L�[�̔z��Ƃ��ĕԋp����B<BR>
     * @@return �T�[�r�X���p�L�[[]
     */
    public WEB3SrvRegiServiceUseKey[] getHashList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashList() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3SrvUseKeyTypeDef.HSAH_VALUE};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            //[���ёւ�]
            // �T�[�r�X���p�L�[ID�ŏ���
            String l_strSortKey = "srv_use_id ASC";
            List l_lisRows = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE, 
                l_strWhere,
                l_strSortKey,
                null,
                l_obj);//DataNetworkException, DataQueryException
                
            WEB3SrvRegiServiceUseKey[] l_useKeys = null;
            
            int l_intCnt = l_lisRows.size();
            
            if (l_lisRows != null &&  l_intCnt > 0)
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[l_intCnt];
                for (int i = 0; i < l_intCnt; i++)
                {
                    SrvRegiKeyRow l_row = (SrvRegiKeyRow)l_lisRows.get(i);
                    l_useKeys[i] = new WEB3SrvRegiServiceUseKey(l_row);
                }
            }
            else
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[0];
            }
            return l_useKeys;
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get���M�p�����[�^�ꗗ ) <BR>
     *���Y�T�[�r�X�ɕR�t�����M�p�����[�^�̈ꗗ��ԋp����B<BR> 
     * <BR>
     *1) �ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR> 
     *[��������] <BR>
     *�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     *�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     *�@@���p�L�[��ʋ敪="���M�p�����[�^" <BR>
     * <BR>
     *2) 1)�̌������ʂ��T�[�r�X���p�L�[�̔z��Ƃ��ĕԋp����B<BR>
     * @@return �T�[�r�X���p�L�[[]
     */
    public WEB3SrvRegiServiceUseKey[] getParamList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getParamList() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3SrvUseKeyTypeDef.SEND_PARAM};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisRows = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE, 
                l_strWhere, l_obj);//DataNetworkException, DataQueryException
                
            WEB3SrvRegiServiceUseKey[] l_useKeys = null;
            
            int l_intCnt = l_lisRows.size();
            
            if (l_lisRows != null &&  l_intCnt > 0)
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[l_intCnt];
                for (int i = 0; i < l_intCnt; i++)
                {
                    SrvRegiKeyRow l_row = (SrvRegiKeyRow)l_lisRows.get(i);
                    l_useKeys[i] = new WEB3SrvRegiServiceUseKey(l_row);
                }
            }
            else
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[0];
            }
            return l_useKeys;
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (createNew�T�[�r�X�}�X�^�[)<BR>
     * �V�K�ɃT�[�r�X�}�X�^�[�I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �T�[�r�X�}�X�^�[Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) �T�[�r�X�}�X�^�[Params.set�،���ЃR�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �T�[�r�X�}�X�^�[Params.set�T�[�r�X�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) �T�[�r�X�}�X�^�[Params.set�\���敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�\���敪=����.�\���敪<BR>
     * <BR>
     * 5) �T�[�r�X�}�X�^�[Params.set�X�e�[�^�X( )���R�[������B<BR>
     * [����]<BR>
     * �@@�X�e�[�^�X="��~��"<BR>
     * <BR>
     * 6) �T�[�r�X�}�X�^�[�̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�T�[�r�X�}�X�^�[�I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�}�X�^�[Row=�쐬�����T�[�r�X�}�X�^�[Params�I�u�W�F�N�g<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strOfferingDiv - (�\���敪)<BR>
     * 0�F�s�v�@@1�F�v<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster
     * @@throws WEB3BaseException
     * @@roseuid 413E60E601C6
     */
    public static WEB3SrvRegiServiceMaster createNewSrvMaster(String l_strInstitutionCode, String l_strSrvDiv, String l_strOfferingDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvMaster(String, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�}�X�^�[Params�I�u�W�F�N�g�𐶐�����
        SrvRegiMasterParams l_masterParams = new SrvRegiMasterParams();
        
        //2) �T�[�r�X�}�X�^�[Params.set�،���ЃR�[�h()���R�[������B
        l_masterParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) �T�[�r�X�}�X�^�[Params.set�T�[�r�X�敪()���R�[������B
        l_masterParams.setSrvDiv(l_strSrvDiv);
        
        //4) �T�[�r�X�}�X�^�[Params.set�\���敪()���R�[������B
        l_masterParams.setOfferingDiv(l_strOfferingDiv);
        
        //5) �T�[�r�X�}�X�^�[Params.set�X�e�[�^�X( )���R�[������B
        l_masterParams.setSrvStatus(WEB3SrvStatusDef.STOP);
        
        //6) �T�[�r�X�}�X�^�[�̃R���X�g���N�^���R�[�����A��������
        WEB3SrvRegiServiceMaster l_serviceMaster = new WEB3SrvRegiServiceMaster(l_masterParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_serviceMaster;
    }
    
    /**
     * (save�T�[�r�X�}�X�^�[)<BR>
     * this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e���f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X�}�X�^�[�e�[�u�����X�V�iUpdate�j����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E60E601E5
     */
    public void saveSrvMaster() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveSrvMaster()  ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B
            this.srvMasterParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvMasterParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //2) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doUpdateQuery(this.srvMasterParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew�T�[�r�X�}�X�^�[)<BR>
     * this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e���f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X�}�X�^�[�e�[�u�����X�V�iInsert�j����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E60E60204
     */
    public void saveNewSrvMaster() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewSrvMaster() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B
            this.srvMasterParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvMasterParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvMasterParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(srvMasterParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (get�t���敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h�t���敪�h��ԋp����B<BR>
     * <BR>
     * 1)�@@�ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * �@@[��������]<BR>
     * �@@�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( )and<BR>
     * �@@�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and <BR>
     * �@@�@@���p�L�[��ʋ敪="�t���敪" and<BR>
     * �@@�@@�T�[�r�X���p�L�[ID=�P�i�Œ�l�j<BR>
     * <BR>
     * 2)�@@�߂�l�̐ݒ�<BR>
     * �@@2-1)�@@1)�̌������ʂ��擾�ł���ꍇ<BR>
     * �@@�@@1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()��<BR>
     * �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@2-2)�@@1)�̌������ʂ��擾�ł��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionDiv()";
        log.entering(STR_METHOD_NAME);

        SrvRegiKeyRow l_srvRegiKeyRow = null;
        //[��������]
        //�،���ЃR�[�h=this.get�،���ЃR�[�h( )
        //�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and
        //���p�L�[��ʋ敪="�t���敪" and
        //�T�[�r�X���p�L�[ID=�P�i�Œ�l)
        try
        {
        	l_srvRegiKeyRow = SrvRegiKeyDao.findRowByPk(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.ADDITION_DIV,
                1l);
        }
        catch (DataFindException l_ex)
        {
            //1)�̌������ʂ��擾�ł��Ȃ��ꍇ�Anull��ԋp����B
            log.debug("�T�[�r�X���p�L�[���擾�o���Ȃ������ꍇ");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1)�̌������ʂ��擾�ł���ꍇ
        //1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()��
        //�߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiKeyRow.getSrvUseKey();
    }

    /**
     * (get�t���敪)<BR>
     * ���Y�T�[�r�X�ɕR�t���h�t���敪�h��ԋp����B<BR>
     * <BR>
     * 1)�@@�ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and<BR>
     * �@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and<BR>
     * �@@���p�L�[��ʋ敪="�t���敪" and<BR>
     * �@@�T�[�r�X���p�L�[ID=����.�T�[�r�X���p�L�[ID<BR>
     * <BR>
     * 2) �߂�l�̐ݒ�<BR>
     * �@@2-1)�@@1)�̌������ʂ��擾�ł���ꍇ<BR>
     * �@@�@@1)�Ŏ擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()��<BR>
     * �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@2-2)�@@1)�̌������ʂ��擾�ł��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_intSrvUseId - (�T�[�r�X���p�L�[ID)<BR>
     * �T�[�r�X���p�L�[ID<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionDiv(int l_intSrvUseId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionDiv(int)";
        log.entering(STR_METHOD_NAME);

        SrvRegiKeyRow l_srvRegiKeyRow = null;
        try
        {
            //�ȉ��̏����Łu�T�[�r�X���p�L�[�v�e�[�u������������
            //[��������]
            //�@@�،���ЃR�[�h=this.get�،���ЃR�[�h( ) and
            //�@@�T�[�r�X�敪=this.get�T�[�r�X�敪( ) and
            //�@@���p�L�[��ʋ敪="�t���敪" and
            //�@@�T�[�r�X���p�L�[ID=����.�T�[�r�X���p�L�[ID
            l_srvRegiKeyRow = SrvRegiKeyDao.findRowByPk(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.ADDITION_DIV,
                l_intSrvUseId);
        }
        catch (DataFindException l_ex)
        {
            //�������ʂ��擾�ł��Ȃ��ꍇ�Anull��ԋp����
            log.debug("�T�[�r�X���p�L�[���擾�o���Ȃ������ꍇ");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł���ꍇ
        //�擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�̖߂�l��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiKeyRow.getSrvUseKey();
    }
}
@
