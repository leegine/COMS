head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopRegCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g(WEB3AdminToTradeStopRegCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���q��(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�o�^�������N�G�X�g�N���X<BR>
 * 
 * @@author ���q��
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopRegCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_reg_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password = null;
    
    /**
     * (�戵��~���)<BR>
     * �戵��~���<BR>
     */
    public WEB3AdminToTradeStopInfoUnit tradeStopInfoUnit;
    
    /**
     * @@roseuid 4430D362037A
     */
    public WEB3AdminToTradeStopRegCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�戵��~���`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�戵��~��� == null�̏ꍇ�A<BR>
     * �@@�@@�u�戵��~��񂪖����́v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02431<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�戵��~���.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�u�����R�[�h�����w��ł��B�v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�戵��~���.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BA3C01B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�戵��~���`�F�b�N
        //�@@�P�|�P�j�@@this.�戵��~��� == null�̏ꍇ�A
        //�@@�@@�u�戵��~��񂪖����́v�̗�O���X���[����B
        if (this.tradeStopInfoUnit == null)
        {
            log.debug("�戵��~��񂪖����͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵��~��񂪖����͂ł��B");
        }        
        
        // �P�|�Q�j�@@this.�戵��~���.�����R�[�h == null�̏ꍇ�A
        //�@@�@@�u�����R�[�h�����w��ł��B�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopInfoUnit.productCode))
        {
            log.debug("�����R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B");
        }
        
        // �P�|�R�j�@@this.�戵��~���.validate()���R�[������B
        this.tradeStopInfoUnit.validate();
        
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�o�^�������X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopRegCompleteResponse(this);
    }
}@
