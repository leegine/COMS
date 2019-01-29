head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�XImpl(WEB3AdminFPTForceLogoutUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.define.WEB3AdminFPTForceLogoutValidityDef;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutUnitService;

import webbroker3.util.WEB3LogUtility;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�XImpl
 * 
 * @@author ��
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutUnitServiceImpl implements WEB3AdminFPTForceLogoutUnitService 
{
	
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutUnitServiceImpl.class);
    
    /**
     * @@roseuid 47DF46770017
     */
    public WEB3AdminFPTForceLogoutUnitServiceImpl() 
    {
     
    }
    
    /**
     * this.���O�C���Z�b�V����Row�̃Z�b�V�����𖳌��ɂ���B
     * 
     * �V�[�P���X�} 
     * �u�i�Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X�jlogout�v�Q�ƁB
     * @@param l_loginSessionRow - ���O�C���Z�b�V����Row
     * @@roseuid 47D664AD026D
     */
    public void logout(LoginSessionRow l_loginSessionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "logout(LoginSessionRow)";
        log.entering(STR_METHOD_NAME);
    	
    	//���O�C���Z�b�V����Params�𐶐�����B
    	//[����]
    	//arg0�F�@@����.���O�C���Z�b�V����Row
    	LoginSessionParams l_params = new LoginSessionParams(l_loginSessionRow);
     
    	//�L���t���O��ݒ肷��B
    	//[����]
    	//arg0�F�@@����
    	l_params.setValidity(WEB3AdminFPTForceLogoutValidityDef.INT_VALIDITY_INVALID_USER_LOGOUT.intValue());
    	
    	//�X�V���Ԃ�ݒ肷��B
    	//[����]
    	//arg0�F�@@new Date()
    	l_params.setLastUpdate(new Date());
    	
    	try {
			
    		final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

			//���b�N����B
			//[����]
			//arg0�F�@@���O�C���Z�b�V����.���O�C��Id
    		//arg1:   true  
			l_queryProcesser.lockAccount(l_loginSessionRow.getLoginId(),true);
			
	    	//���O�C���𖳌��ɂ���B
	    	//[����]
	    	//arg0�F�@@��������LoginSessionParams
			l_queryProcesser.doUpdateQuery(l_params);
			
		} catch (Exception l_ex) {
	           log.exiting(STR_METHOD_NAME);
	           throw new WEB3BaseException(
	               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	               this.getClass().getName() + "." + STR_METHOD_NAME,
	               l_ex.getMessage(), l_ex);
		}
    	
		log.exiting(STR_METHOD_NAME);
    }
}
@
