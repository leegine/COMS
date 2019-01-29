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
filename	WEB3GentradeMailInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  ���[��(WEB3GentradeMailInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.MailInfoDao;
import webbroker3.gentrade.data.MailInfoPK;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * ���[���@@�G���e�B�e�B�N���X
 */
public class WEB3GentradeMailInfo implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMailInfo.class);
        

    /**
     * ���[���s
     */
    private MailInfoParams mailInfoParams;

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * 1) ����.���[��Params��this.���[���s�ɐݒ肷��B<BR>
     * @@param l_mailInfoParams - ���[��Params
     * @@roseuid 413C1F1E02EE
     */
    public WEB3GentradeMailInfo(MailInfoParams l_mailInfoParams)
    {
        final String STR_METHOD_NAME = "WEB3GentradeMailInfo(MailInfoParams)";
        if (l_mailInfoParams == null)
        {
            log.error("���[���s = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���s = null");
        }
        this.mailInfoParams = l_mailInfoParams;
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * 1) ���[���e�[�u�������������[��Params���擾����B<BR>
     * [��������]<BR>
     * �،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * ���M���[���敪=����.���M���[���敪 and<BR>
     * ����ID=����.����ID<BR>
     *  <BR>
     * 2) ���[��Params���擾�ł����ꍇ�́A�ȉ��̏������s���B<BR>
     * 2-1) �擾�������[��Params��this.���[���s�ɐݒ肷��B<BR>
     *  <BR>
     * 3) �o���Ȃ������ꍇ�A��O���X���[����B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSendmailDiv - ���M���[���敪
     * @@param l_strDiscernmentId - ����ID
     * @@throws WEB3BaseException
     * @@roseuid 41049A93006D
     */
    public WEB3GentradeMailInfo(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
	    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeMailInfo(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) ���[���e�[�u�������������[��Params���擾����B
        // [��������]
        //  �،���ЃR�[�h=����.�،���ЃR�[�h and
        //  ���M���[���敪=����.���M���[���敪 and
        //  ����ID=����.����ID     
        MailInfoRow l_mailInfoRow = null;
        try
        {
            l_mailInfoRow = MailInfoDao.findRowByPk(
                l_strInstitutionCode,
                l_strSendmailDiv,
                l_strDiscernmentId);
        }
        catch (DataFindException dfe)
        {
            log.debug("���[��Params���擾�ł��Ȃ������ꍇ");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
                "���[���e�[�u�������������[��Params���擾���Ȃ�");
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
        
        // �擾�������[��Params��this.���[���s�ɐݒ肷��B
        this.mailInfoParams = new MailInfoParams(l_mailInfoRow);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.���[���s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 413419BF01DD
     */
    public Object getDataSourceObject()
    {
        return this.mailInfoParams;
    }

    /**
     * (createForUpdateParams)<BR>
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.���[���s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B<BR> 
     * �쐬�����R�s�[�����g��this.���[���s�ɃZ�b�g����B<BR>
     * @@roseuid 413419BF01ED
     */
    public void createForUpdateParams()
    {
        MailInfoParams l_mailInfoParams = 
            new MailInfoParams(this.mailInfoParams);
        this.mailInfoParams = l_mailInfoParams;

    }

    /**
     * (createNew���[��)<BR>
     * �V�������[���I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * <BR>
     * 1) ���[��Params�I�u�W�F�N�g�𐶐�����B<BR>
     *  <BR>
     * 2) ���[��Params�I�u�W�F�N�g�̈ȉ��̃v���p�e�B�ɒl���Z�b�g����B<BR>
     * �،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * ���M���[���敪=����.���M���[���敪<BR>
     * ����ID=����.����ID<BR>
     *  <BR>
     * 3) ���[��Params�I�u�W�F�N�g�������ɁA�R���X�g���N�^���R�[�����A<BR>
     * �擾�������[���I�u�W�F�N�g��ԋp����B<BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSendmailDiv - ���M���[���敪
     * @@param l_strDiscernmentId - ����ID
     * @@return webbroker3.��ЁE�ڋq�G���e�B�e�B.WEB3GentradeMailInfo
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D4138007D
     */
    public static WEB3GentradeMailInfo createNewMail(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
    {
        //1) ���[��Params�I�u�W�F�N�g�𐶐�����
        MailInfoParams l_mailInfoParams = new MailInfoParams();
        
        //2) ���[��Params�I�u�W�F�N�g�̈ȉ��̃v���p�e�B�ɒl���Z�b�g����
        l_mailInfoParams.setInstitutionCode(l_strInstitutionCode);
        l_mailInfoParams.setSendmailDiv(l_strSendmailDiv);
        l_mailInfoParams.setDiscernmentId(l_strDiscernmentId);
        
        //3) ���[��Params�I�u�W�F�N�g�������ɁA�R���X�g���N�^���R�[�����A
        //�擾�������[���I�u�W�F�N�g��ԋp����B
        return new WEB3GentradeMailInfo(l_mailInfoParams);
    }

    /**
     * (save���[��) <BR>
     * ����.���[���̓��e���f�[�^�x�[�X�ɔ��f������B<BR>
     * <BR>
     * 1) this.���[���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     *   �X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h()
     * <BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
     * <BR>
     * 2) �����������[��Params�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@���[���e�[�u�����X�V�iUpdate�j����B<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D4138009C
     */
    public void saveMail()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveMail()";
        log.entering(STR_METHOD_NAME);
        
        // �X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h()
        String l_strAdministratorCode =  
            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
		//�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
                        
        //4000�o�C�g�ΏۊO�̊e���� 
        Map l_comMap = new HashMap();
        //���[������
        	l_comMap.put("mail_name",this.mailInfoParams.getMailName());
        //���o�l
        	l_comMap.put("mail_sender", this.mailInfoParams.getMailSender());
        //����
        	l_comMap.put("subject", this.mailInfoParams.getSubject());      	
        //���M��A�h���X
        	l_comMap.put("send_address", this.mailInfoParams.getSendAddress());
        //�X�V�҃R�[�h
		l_comMap.put("last_updater", l_strAdministratorCode);
        //�X�V���t
		l_comMap.put("last_updated_timestamp", l_tsSystemTime);
		
		//BatchedQuery[]�̍쐬�p
		ArrayList l_listQuery = new ArrayList();
        
		//4000byte�ΏۊObatchedquery�쐬
		BatchedQuery l_comQuery = BatchedQuery.createUpdateQuery(
				new MailInfoPK( 
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_comMap);
		l_listQuery.add(l_comQuery);
        		
        //4000byte�Ώۍ���
        //header
		Map l_header = new HashMap();
		        	
		l_header.put("mail_header", this.mailInfoParams.mail_header);
		this.mailInfoParams.mail_header = null;
			
		BatchedQuery l_updQueryHeader = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_header);        	
        	
		l_listQuery.add(l_updQueryHeader);

		//text(body)
		Map l_text = new HashMap();
		        	
		l_text.put("mail_text", this.mailInfoParams.mail_text);
		this.mailInfoParams.mail_text = null;
			
		BatchedQuery l_updQueryText = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_text);        	
        	
		l_listQuery.add(l_updQueryText);
	

		//footer
		Map l_footer = new HashMap();
		        	
		l_footer.put("mail_footer", this.mailInfoParams.mail_footer);
		this.mailInfoParams.mail_footer = null;
			
		BatchedQuery l_updQueryFooter = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_footer);        	
        	
		l_listQuery.add(l_updQueryFooter);
                
        BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()]; 
        
        l_listQuery.toArray(l_queries);   
        		
        // ���[���e�[�u����V�K�o�^�A�X�V(BatchedQuery)������
        try
        {
			QueryProcessor l_processer = Processors.getDefaultProcessor();
			l_processer.doQueries(l_queries); 
        }
        catch (DataException dataEx)
        {
            log.error(STR_METHOD_NAME, dataEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dataEx.getMessage(),
                dataEx);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveNew���[��)<BR>
     * ����.���[���̓��e���f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     *  <BR>
     * 1) this.���[���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�X�V�҃R�[�h = <BR>
     *        �Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( ) <BR>
     * �@@�@@�쐬���t = <BR>
     *        GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�@@�X�V���t = <BR>
     *        GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     *  <BR>
     * 2) this.���[���s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@���[���e�[�u�����X�V�iInsert�j����B<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D881E039A
     */
    public void saveNewMail()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveNewMail()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.���[���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
        //�X�V�҃R�[�h = �Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )         
        String l_strAdministratorCode =  
            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.mailInfoParams.setLastUpdater(l_strAdministratorCode);
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
		//�X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.mailInfoParams.setLastUpdatedTimestamp(l_tsSystemTime);
		//�쐬���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.mailInfoParams.setCreatedTimestamp(l_tsSystemTime);
        
		//4000byte�Ώۍ���
		Hashtable l_hashTable = new Hashtable();
		//BatchedQuery[]�̍쐬�p
		ArrayList l_list = new ArrayList();
       	
		//header���͂���
		if(this.mailInfoParams.mail_header != null){
			l_hashTable.put("mail_header", this.mailInfoParams.mail_header);
			this.mailInfoParams.mail_header = null;
		}
		//text���͂���
		if(this.mailInfoParams.mail_text != null){
			l_hashTable.put("mail_text", this.mailInfoParams.mail_text);
			this.mailInfoParams.mail_text = null;
		}
		//footer���͂���
		if(this.mailInfoParams.mail_footer != null){
			l_hashTable.put("mail_footer", this.mailInfoParams.mail_footer);
			this.mailInfoParams.mail_footer = null;
		}
		
		//BatchedQuery�쐬
		//ins�i4000�o�C�g�ΏۊO�̊e����)
		BatchedQuery l_insQuery = BatchedQuery.createInsertQuery(this.mailInfoParams);
		l_list.add(l_insQuery);
		
		//upd�i4000byte�Ώہj
		Enumeration l_enum = l_hashTable.keys();
		//�X�V�Ώۂ���̏ꍇ�A��̃}�b�v�I�u�W�F�N�g�ɑ΂��ėv�f�����P�ɂ�
		//BatchedQuery�I�u�W�F�N�g�̍쐬
		while(l_enum.hasMoreElements()){
        	
			String l_key = (String) l_enum.nextElement();
			
			Map l_map = new HashMap();
        	
			l_map.put(l_key, l_hashTable.get(l_key));
			
			BatchedQuery l_updQuery = 
				BatchedQuery.createUpdateQuery(
					new MailInfoPK(
						this.mailInfoParams.getInstitutionCode(),
						this.mailInfoParams.getSendmailDiv(),
						this.mailInfoParams.getDiscernmentId()),
					l_map);        	
        	
			l_list.add(l_updQuery);
        	
		}
		
		BatchedQuery[] l_queries = new BatchedQuery[l_list.size()]; 
        
		l_list.toArray(l_queries);
        
		// ���[���e�[�u����V�K�o�^�A�X�V(BatchedQuery)������
		try
		{
			QueryProcessor l_processer = Processors.getDefaultProcessor();
			l_processer.doQueries(l_queries); 
		}
		catch (DataException dataEx)
		{
			log.error(STR_METHOD_NAME, dataEx);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				dataEx.getMessage(),
				dataEx);
		}

    }

    /**
     * (remove���[��)<BR>
     * ���[�����f�[�^�x�[�X����폜����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA���Y���R�[�h�����[���e�[�u�����폜����B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@���M���[���敪=����.���M���[���敪 and<BR>
     * �@@����ID=����.����ID<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strSendmailDiv - ���M���[���敪
     * @@param l_strDiscernmentId - ����ID
     * @@roseuid 413D413800CB
     */
    public static void removeMail(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "removeMail(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) �ȉ��������ɁA���Y���R�[�h�����[���e�[�u�����폜����B
        // �،���ЃR�[�h=����.�،���ЃR�[�h and
        // ���M���[���敪=����.���M���[���敪 and
        // ����ID=����.����ID
        try
        {
            MailInfoPK l_mailInfoPK =
                new MailInfoPK(
                    l_strInstitutionCode,
                    l_strSendmailDiv,
                    l_strDiscernmentId);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_mailInfoPK);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailInfo.class + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get�،���ЃR�[�h) <BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.���[���s.get�،���ЃR�[�h( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 41049E48032D
     */
    public String getInstitutionCode()
    {
        return this.mailInfoParams.getInstitutionCode();
    }

    /**
     * (get���M���[���敪) <BR>
     * ���M���[���敪��Ԃ��B<BR>
     * <BR>
     * this.���[���s.get���M���[���敪( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 41049E5E0010
     */
    public String getSendmailDiv()
    {
        return this.mailInfoParams.getSendmailDiv();
    }

    /**
     * (get����ID) <BR>
     * ���[��ID��Ԃ��B<BR>
     * <BR>
     * this.���[���s.get����ID( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 41049E7D010A
     */
    public String getDiscernmentId()
    {
        return this.mailInfoParams.getDiscernmentId();
    }

    /**
     * (set���[������) <BR>
     * ���[�����̂̐ݒ���s���B<BR>
     * <BR>
     * 1) this.���[���s.set���[������( )���R�[������B<BR>
     * [����]<BR>
     * �@@���[������=����.���[������<BR>
     * @@param l_strMailName - ���[������
     * @@roseuid 413C4B440036
     */
    public void setMailName(String l_strMailName)
    {
        this.mailInfoParams.setMailName(l_strMailName);
    }

    /**
     * (get���[������) <BR>
     * ���[�����̂�Ԃ��B<BR>
     * <BR>
     * this.���[���s.get���[������( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 413C4B440064
     */
    public String getMailName()
    {
        return this.mailInfoParams.getMailName();
    }

    /**
     * (set���o�l)<BR>
     * ���o�l�̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@this.���[���s.set���o�l()���R�[������B<BR>
     * [����]<BR>
     * �@@���o�l=����.���o�l<BR>
     * @@param l_strMailSender - ���o�l
     * @@roseuid 41049EB30119
     */
    public void setMailSender(String l_strMailSender)
    {
        this.mailInfoParams.setMailSender(l_strMailSender);
    }

    /**
     * (get���o�l) <BR>
     * ���o�l��Ԃ��B <BR>
     *  <BR>
     * this.���[���s.get���o�l( )�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 41049F2E03D8
     */
    public String getMailSender()
    {
        return this.mailInfoParams.getMailSender();
    }

    /**
     * (set����) <BR>
     * �����̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@this.���[���s.set����( )���R�[������B<BR>
     * [����]<BR>
     * �@@����=����.����<BR>
     * @@param l_strSubject - ����
     * @@roseuid 41049F690158
     */
    public void setSubject(String l_strSubject)
    {
        this.mailInfoParams.setSubject(l_strSubject);
    }

    /**
     * (get����) <BR>
     * ������Ԃ��B<BR>
     * <BR>
     * this.���[���s.get����( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 41049F690167
     */
    public String getSubject()
    {
        return this.mailInfoParams.getSubject();
    }

    /**
     * (set���[���w�b�_�[) <BR>
     * ���[���w�b�_�[�̐ݒ���s���B <BR>
     *  <BR>
     * �P�j�@@this.���[���s.set���[���w�b�_�[( )���R�[������B <BR>
     * [����] <BR> 
     * �@@���[���w�b�_�[=����.���[���w�b�_�[ <BR>
     * @@param l_strMailHeader - ���[���w�b�_�[
     * @@roseuid 41049FBF009C
     */
    public void setMailHeader(String l_strMailHeader)
    {
        this.mailInfoParams.setMailHeader(l_strMailHeader);
    }

    /**
     * (get���[���w�b�_�[) <BR>
     * ���[���w�b�_�[��Ԃ��B<BR>
     * <BR>
     * this.���[���s.get���[���w�b�_�[( )�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 41049FBF00AC
     */
    public String getMailHeader()
    {
        return this.mailInfoParams.getMailHeader();
    }

    /**
     * (set���[���{��) <BR>
     * ���[���{���̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@this.���[���s.set���[���{��()���R�[������B<BR>
     * [����]<BR>
     * �@@���[���{��=����.���[���{��<BR>
     * @@param l_strMailText - ���[���{��
     * @@roseuid 4104A03B005E
     */
    public void setMailText(String l_strMailText)
    {
        this.mailInfoParams.setMailText(l_strMailText);
    }

    /**
     * (get���[���{��) <BR>
     * ���[���{����Ԃ��B<BR>
     * <BR>
     * this.���[���s.get���[���{��()�̖߂�l��Ԃ��B<BR>
     * @@return java.lang.String
     * @@roseuid 4104A03B008D
     */
    public String getMailText()
    {
        return this.mailInfoParams.getMailText();
    }

    /**
     * (set���[���t�b�^�[) <BR>
     * ���[���t�b�^�[�̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@this.���[���s.set���[���t�b�^�[()���R�[������B<BR>
     * [����]<BR>
     * �@@���[���t�b�^�[=����.���[���t�b�^�[<BR>
     * @@param l_strMailFooter - ���[���t�b�^�[
     * @@roseuid 4104A1A800BC
     */
    public void setMailFooter(String l_strMailFooter)
    {
        this.mailInfoParams.setMailFooter(l_strMailFooter);
    }

    /**
     * (get���[���t�b�^�[) <BR>
     * ���[���t�b�^�[��Ԃ��B <BR>
     *  <BR>
     * this.���[���s.get���[���t�b�^�[( )�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     * @@roseuid 4104A1A800BE
     */
    public String getMailFooter()
    {
        return this.mailInfoParams.getMailFooter();
    }
    
    /**
     * (set���M��A�h���X) <BR>
     * ���M��A�h���X�̐ݒ���s���B<BR>
     * <BR>
     * �P�j�@@this.���[���s.set���M��A�h���X()���R�[������B<BR>
     * [����]<BR>
     * �@@���M��A�h���X=����.���M��A�h���X<BR>
     * @@param l_strSendAddress  - ���M��A�h���X
     */
    public void setSendAddress(String l_strSendAddress)
    {
        this.mailInfoParams.setSendAddress(l_strSendAddress);
    }

    /**
     * (get���M��A�h���X) <BR>
     * ���M��A�h���X��Ԃ��B <BR>
     *  <BR>
     * this.���[���s.get���M��A�h���X( )�̖߂�l��Ԃ��B <BR>
     * @@return java.lang.String
     */
    public String getSendAddress()
    {
        return this.mailInfoParams.getSendAddress();
    }
}
@
