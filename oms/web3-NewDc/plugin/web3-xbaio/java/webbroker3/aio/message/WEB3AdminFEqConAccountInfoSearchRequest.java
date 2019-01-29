head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountInfoSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������񌟍����N�G�X�g(WEB3AdminFEqConAccountInfoSearchRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O��������񌟍����N�G�X�g)<BR>
 * �O��������񌟍����N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountInfoSearchRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_info_search";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�O�������ԍ�)<BR>
     * �O�������ԍ�
     */
    public String fstkAccountCode;
    
    /**
     * @@roseuid 423554FD03B9
     */
    public WEB3AdminFEqConAccountInfoSearchRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountInfoSearchRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *   this.���X�R�[�h == null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�O�������ԍ�<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *   this.�O�������ԍ� == null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01953<BR>
     * <BR>
     * �Q�|�Q�j<BR>
     *   this.�O�������ԍ� != �����̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01954<BR>
     * <BR>
     * �Q�|�R�j<BR>
     *   this.�O�������ԍ�.length != 9�̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01954<BR>
     * <BR>
     * @@roseuid 41E611A9026F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h
        //this.���X�R�[�h == null
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        //�Q�j�O�������ԍ� 
        //�Q�|�P�j 
        //this.�O�������ԍ� == null
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.fstkAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01953,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ԍ������w��ł��B");
        }

        //�Q�|�Q�j 
        //this.�O�������ԍ� != ����
        //�̏ꍇ�A��O���X���[����B 
        if(!WEB3StringTypeUtility.isNumber(this.fstkAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ԍ� != �����B");
        }
        
        //�Q�|�R�j 
        //this.�O�������ԍ�.length != 6
        //�̏ꍇ�A��O���X���[����B 
        if(this.fstkAccountCode.length() != 6)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ԍ�.length != 6�B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O��������񌟍����X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountInfoSearchResponse(this);
    }
}
@
