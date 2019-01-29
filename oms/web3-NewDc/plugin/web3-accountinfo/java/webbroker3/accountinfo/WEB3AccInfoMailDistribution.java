head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistribution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ē����[���z�M�w���N���X(WEB3AccInfoMailDistribution)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.accountinfo.data.InformationMailRequestDao;
import webbroker3.accountinfo.data.InformationMailRequestParams;
import webbroker3.accountinfo.data.InformationMailRequestRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (�ē����[���z�M�w���N���X)<BR>
 * �ē����[���z�M�w���N���X<BR>
 *
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3AccInfoMailDistribution implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailDistribution.class);
    /**
     * (�ē����[���z�M�w���s)<BR>
     * �ē����[���z�M�w���s�I�u�W�F�N�g<BR>
     * <BR>
     * ���ē����[���z�M�w��Params�N���X��DDL��莩����������B<BR>
     */
    private InformationMailRequestParams informationMailRequestParams;
    
    /**
     * (�ē����[���̎��ʂh�c)<BR>
     * �ē����[���̎��ʂh�c<BR>
     */
    public static final String DISCERNMENTID = "----";
    /**
     * (�ē����[���̑��M���[���敪)<BR>
     * �ē����[���̑��M���[���敪<BR>
     */
    public static final String SENDMAILDIV = "0401";
    
    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�ē����[���z�M�w���s��ԋp����B  <BR>
     * @@return Object
     * @@roseuid 413D6E360076
     */
    public Object getDataSourceObject()
    {
        return this.informationMailRequestParams;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�ē����[���z�M�w���s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getInstitutionCode()
    {
        return this.informationMailRequestParams.getInstitutionCode();
    }
    /**
     * (get���ʂh�c)<BR>
     * this.�ē����[���z�M�w���s.���ʂh�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getDiscernmentId()
    {
        return this.informationMailRequestParams.getDiscernmentId();
    }
    /**
     * (get���M���[���敪)<BR>
     * this.�ē����[���z�M�w���s.���M���[���敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getSendMailDiv()
    {
        return this.informationMailRequestParams.getSendmailDev();
    }
    /**
     * (is�S�ڋq)<BR>
     * this.�ē����[���z�M�w���s.�S�ڋq�t���O�ɑΉ�����boolean�l��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 413D9F040113
     */
    public boolean isAllAccount()
    {
        BooleanEnum l_booleanEnum = this.informationMailRequestParams.getAllAccountFlag();
        if(BooleanEnum.TRUE.equals(l_booleanEnum))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * (get�z�M�w������)<BR>
     * this.�ē����[���z�M�w���s.�z�M�w��������ԋp����B<BR>
     * @@return Date
     * @@roseuid 413D9F040113
     */
    public Date getRequestTimestamp()
    {
        return this.informationMailRequestParams.getRequestTimestamp();
    }
    /**
     * (is�z�M��)<BR>
     * email�z�M�ς݂��𔻒肷��B<BR>
     *�ithis.�ē����[���z�M�w���s.�z�M�X�e�[�^�X == 1�F�����ρj�̏ꍇtrue<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 413D9F040113
     */
    public boolean isMailRequestStatus()
    {
        String l_strStatus = this.informationMailRequestParams.getStatus();
        if(WEB3StatusDef.DEALT.equals(l_strStatus))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * (get�X�V�҃R�[�h)<BR>
     * this.�ē����[���z�M�w���s.�X�V�҃R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getLastUpdater()
    {
        return this.informationMailRequestParams.getLastUpdater();
    }
    /**
     * (get�z�M�ڋq��)<BR>
     * this.�ē����[���z�M�w���s.�z�M�ڋq����ԋp����B<BR>
     * @@return long
     * @@roseuid 413D9F040113
     */
    public long getAccountCount()
    {
        return this.informationMailRequestParams.getAccountCount();
    }
    /**
     * (get�ē����[������)<BR>
     * �ē����[���̌������擾����B<BR>
     * �ȉ��̏����Ń��[���e�[�u������������B<BR>
     * [����]<BR>
�@@   * ���[���e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
�@@   * ���[���e�[�u��.���M���[���敪 = �ē����[���z�M�w��.�ē����[�����M���[���敪 And<BR>
�@@   * ���[���e�[�u��.���ʂh�c = �ē����[���z�M�w��.�ē����[�����ʂh�c<BR>
     * �Y���s�̌�����ԋp����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ�A�ē����[�������[���e�[�u���ɑ��݂��Ȃ��Ɣ��f���A��O���X���[����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getMailSubject() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMailSubject()";
        log.entering(STR_METHOD_NAME);
        
        //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");        
        l_sbWhere.append(" and sendmail_div = ? ");       
        l_sbWhere.append(" and discernment_id = ? ");       
        
        List l_lisWhere = new ArrayList();
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strSendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        String l_strDiscernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        l_lisWhere.add(l_strInstitutionCode);
        l_lisWhere.add(l_strSendMailDiv);
        l_lisWhere.add(l_strDiscernId);
        
        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null; 
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        int l_intSize = l_lisRecords.size();
        if (l_intSize != 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01379,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "�ē����[���̌��������݂��Ȃ��ꍇ " );

        }

        MailInfoRow[] l_row = new MailInfoRow[l_intSize];
        l_lisRecords.toArray(l_row);

        String l_strSubject = l_row[0].getSubject();
        
        log.exiting(STR_METHOD_NAME);    
        return l_strSubject;
    }
    /**
     * (�ē����[���z�M�w��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_informationMailRequestParams - �ē����[���z�M�w���s�I�u�W�F�N�g<BR>
     * <BR>
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoMailDistribution(InformationMailRequestParams l_informationMailRequestParams)
    {
        this.informationMailRequestParams = l_informationMailRequestParams;
    }
    /**
     * (�ē����[���z�M�w��)<BR>
     * �R���X�g���N�^<BR>
     * �w�肵���ē����[���z�M�w���h�c�ɊY������s���ē����[���z�M�w���e�[�u����茟������B <BR>
     * �Y���s�����݂��Ȃ��ꍇ�́A��O���X���[����B<BR>
     * �������ʂ̈ē����[���z�M�w���s�I�u�W�F�N�g�������Ɏw�肵�āA�R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_lngInformationMailRequestId - �ē����[���z�M�w���h�c
     * @@return WEB3AccInfoMailDistribution
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoMailDistribution(long l_lngInformationMailRequestId) throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccInfoMailDistribution(long)";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.informationMailRequestParams = (InformationMailRequestParams)InformationMailRequestDao.findRowByInformationMailRequestId(l_lngInformationMailRequestId);
            //WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution();
            
        }
        catch (DataFindException e)
        {
            throw new NotFoundException(
                "No WEB3AccInfoMailDistribution could be found with id : " + l_lngInformationMailRequestId);
        } 
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂���"
                );
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂���"
                );
        }
        if (this.informationMailRequestParams == null)
        {
            //�Y���s���Ȃ��ꍇ�́A��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
           
    }
    /**
     * (get�ē����[���z�M�w���h�c)<BR>
     * this.�ē����[���z�M�w��.�ē����[���z�M�w���h�c��ԋp����B<BR>
     * @@return long
     * @@roseuid 413D9F040113
     */
    public long getInformationMailRequestId()
    {
        return this.informationMailRequestParams.getInformationMailRequestId();
    }
    /**
     * (get�ŐV����)<BR>
     * �istatic���\�b�h�j<BR>
     * �ē����[���z�M�w���̍ŐV�������擾����B<BR>
     * �ȉ��̏����ňē����[���z�M�w���e�[�u������������B<BR>
�@@   * [����]<BR>
�@@   * �ē����[���z�M�w���e�[�u��.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
�@@   * �ē����[���z�M�w���e�[�u��.���M���[���敪 = �ē����[���z�M�w��.�ē����[�����M���[���敪 And<BR>
�@@   * �ē����[���z�M�w���e�[�u��.���ʂh�c = �ē����[���z�M�w��.�ē����[�����ʂh�c<BR>
�@@   * [�擾��]<BR>
�@@   * �z�M�w������ �~���i�Fdesc�j�@@<BR>
     * �������ʂ̂P�Ԗځiindex=0�j���w�肵�A�ē����[���z�M�w���I�u�W�F�N�g�𐶐�����B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * �Y���s���Ȃ��ꍇ��null��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     *<BR>
     * @@return webbroker3.accountinfo.WEB3AccInfoMailDistribution<BR>
     * @@roseuid 413D9F040113
     */
    public static WEB3AccInfoMailDistribution getLastAction(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastAction(String)";
        log.entering(STR_METHOD_NAME);
        
        //�ڋq�}�X�^�e�[�u�����ȉ��̏����Ō�������
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");        
        l_sbWhere.append(" and sendmail_dev = ? ");       
        l_sbWhere.append(" and discernment_id = ? ");       
        
        //�擾���iorder by�j
        StringBuffer l_sbOrderBy = new StringBuffer();
        l_sbOrderBy.append(" request_timestamp desc ");

        
        List l_lisWhere = new ArrayList();
        String l_strSendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        
        String l_strDiscernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        l_lisWhere.add(l_strInstitutionCode);
        l_lisWhere.add(l_strSendMailDiv);
        l_lisWhere.add(l_strDiscernId);
        
        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null; 
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformationMailRequestRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {
            InformationMailRequestParams l_params = (InformationMailRequestParams)l_lisRecords.get(0);
            WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution(l_params);
            log.exiting(STR_METHOD_NAME);    
            return l_mailDistribution;    
        }
        
        
    }
    /**
     * (createNew�ē����[���z�M�w��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ē����[���z�M�w���V�K�s�𐶐�����B<BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
�@@   * �ē����[���z�M�w��Params�I�u�W�F�N�g�𐶐�����B<BR>
�@@   * ���ē����[���z�M�w��Params��DDL��莩����������B<BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR>
�@@   * �P�j�Ő��������ē����[���z�M�w��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B <BR>
�@@   *     �ē����[���z�M�w��Params.�ē����[���z�M�w���h�c = �V�K�̔�(*1)<BR>
�@@   *     �ē����[���z�M�w��Params.�،���ЃR�[�h = �،���ЃR�[�h<BR>
�@@   *     �ē����[���z�M�w��Params.���M���[���敪 = �ē����[���z�M�w��.�ē����[�����M���[���敪<BR>
�@@   *     �ē����[���z�M�w��Params.����ID = �ē����[���z�M�w��.�ē����[�����ʂh�c<BR>
�@@   *     �ē����[���z�M�w��Params.�S�ڋq�t���O = is�S�ڋq�ɑΉ�����BooleanEnum�l<BR>
�@@   *     �ē����[���z�M�w��Params.�z�M�ڋq�� = �ē����[���z�M�w��.calc�z�M�ڋq��(is�S�ڋq)<BR>
�@@   *     �ē����[���z�M�w��Params.�z�M�w������ = TradingSystem.getSystemTimestamp() <BR>
�@@   *     �ē����[���z�M�w��Params.�z�M�X�e�[�^�X = 0�F�������iEmail���z�M�j<BR>
�@@   *     �ē����[���z�M�w��Params.�X�V�҃R�[�h = �X�V�҃R�[�h<BR>
�@@   *     �ē����[���z�M�w��Params.�쐬���� = TradingSystem.getSystemTimestamp() <BR>
�@@   *     �ē����[���z�M�w��Params.�X�V���� = TradingSystem.getSystemTimestamp() <BR>
�@@   *  (*1) �ē����[���z�M�w���h�c�̐V�K�̔� <BR>
�@@   *        �ē����[���z�M�w��DAO.newPkValue()���\�b�h�ɂĎ擾����B <BR>
�@@   *        �� �ē����[���z�M�w��DAO�N���X��DDL��莩����������B <BR>
     * �R�j�@@�ē����[���z�M�w���I�u�W�F�N�g�ԋp<BR>
�@@   *       �s�I�u�W�F�N�g���w�肵�A�ē����[���z�M�w���I�u�W�F�N�g�𐶐����ԋp����B<BR>
�@@   *       [�R���X�g���N�^�̈���]<BR>
�@@   *       �ē����[���z�M�w���s�F�@@�i�Q�j�Ő��������s�I�u�W�F�N�g�j<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_blnAllAccountFlag - is�S�ڋq<BR>
     * @@param l_strLastUpdater - �X�V�҃R�[�h<BR>
     *<BR>
     * @@return webbroker3.accountinfo.WEB3AccInfoMailDistribution<BR>
     * @@roseuid 413D9F040113
     */
    public static WEB3AccInfoMailDistribution createNewMailDistribution(String l_strInstitutionCode, 
        boolean l_blnAllAccountFlag, String l_strLastUpdater) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewMailDistribution(String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        InformationMailRequestParams l_mailRequestParams = new InformationMailRequestParams();
        long l_lngInformationMailRequestId = 0L;
        try
        {
            l_lngInformationMailRequestId = InformationMailRequestDao.newPkValue();
        }
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        l_mailRequestParams.setInformationMailRequestId(l_lngInformationMailRequestId);
        l_mailRequestParams.setInstitutionCode(l_strInstitutionCode);
        l_mailRequestParams.setSendmailDev(WEB3AccInfoMailDistribution.SENDMAILDIV);
        l_mailRequestParams.setDiscernmentId(WEB3AccInfoMailDistribution.DISCERNMENTID);
        if (l_blnAllAccountFlag)
        {
            l_mailRequestParams.setAllAccountFlag(BooleanEnum.TRUE);
        }
        else 
        {
            l_mailRequestParams.setAllAccountFlag(BooleanEnum.FALSE);
        }
        l_mailRequestParams.setAccountCount(WEB3AccInfoMailDistribution.calcAccountCount(l_strInstitutionCode, l_blnAllAccountFlag));
        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        l_mailRequestParams.setRequestTimestamp(l_systemTimestamp);
        l_mailRequestParams.setCreatedTimestamp(l_systemTimestamp);
        l_mailRequestParams.setLastUpdatedTimestamp(l_systemTimestamp);
        l_mailRequestParams.setLastUpdater(l_strLastUpdater);
        l_mailRequestParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
        
        WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution(l_mailRequestParams);
        
        log.exiting(STR_METHOD_NAME);        
        return l_mailDistribution;
        
    }
    /**
     * (calc�z�M�ڋq��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ē����[���z�M�Ώیڋq�����v�Z����B<BR>
     * <BR>
     * �ڋq�}�X�^���ȉ��̏����Ō������A��v����s����ԋp����B<BR>
     * �|�S�ڋq�̏ꍇ��1<BR>
     * <BR>
     * [����]<BR>
     * �ڋq�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h()<BR>
     * �|��]�q�̏ꍇ��2<BR>
     * <BR>
     * [����]<BR>
     * �ڋq�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h()�@@And<BR>
     * �ڋq�}�X�^.�ē����[�����M�t���O = BooleanEnum.TRUE<BR>
     * ��1 �S�ڋq�̔���F�@@�ithis.is�S�ڋq() == true�j<BR>
     * ��2 ��]�q�̔���F�@@�ithis.is�S�ڋq() == false�j<BR>
     * <BR>
     * @@param l_blnAllAccountFlag - is�S�ڋq<BR>
     * <BR>
     * @@return long <BR>
     * @@roseuid 413D9F040113
     */
    public static long calcAccountCount(String l_strInstitutionCode, boolean l_blnAllAccountFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMailSubject()";
        log.entering(STR_METHOD_NAME);
        
        if (l_blnAllAccountFlag)
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        

            List l_lisWhere = new ArrayList();
            //String l_strInstitutionCode = this.getInstitutionCode();

            l_lisWhere.add(l_strInstitutionCode);
        
            Object[] l_objWhere = l_lisWhere.toArray();

            QueryProcessor l_queryProcessor = null; 
            long l_intSize = 0;
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_intSize = l_queryProcessor.doGetCountQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataFindException l_e) 
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }  
            catch (DataQueryException l_e) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            } 
            catch (DataNetworkException l_e) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            log.exiting(STR_METHOD_NAME);    
            return l_intSize;
        }
        else 
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        
            l_sbWhere.append(" and information_mail_flag = ? ");
            
            List l_lisWhere = new ArrayList();
            //String l_strInstitutionCode = this.getInstitutionCode();

            l_lisWhere.add(l_strInstitutionCode);
            l_lisWhere.add(BooleanEnum.TRUE);
        
            Object[] l_objWhere = l_lisWhere.toArray();

            QueryProcessor l_queryProcessor = null; 
            long l_intSize = 0; 
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_intSize = l_queryProcessor.doGetCountQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataFindException l_e) 
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }  
            catch (DataQueryException l_e) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            } 
            catch (DataNetworkException l_e) 
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }
        

            log.exiting(STR_METHOD_NAME);    
            return l_intSize;
        }

    }
    
    
    
    
}
@
