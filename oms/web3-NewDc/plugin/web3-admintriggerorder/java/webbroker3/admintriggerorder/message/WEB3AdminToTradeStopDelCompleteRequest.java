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
filename	WEB3AdminToTradeStopDelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g(WEB3AdminToTradeStopDelCompleteRequest.java)
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
 * (�g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�폜�������N�G�X�g�N���X<BR>
 * 
 * @@author ���q��
 * @@version 1.0   
 */
public class WEB3AdminToTradeStopDelCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopDelCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_del_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200604031700L;
    
    /**
     * (���ꎷ�s�����戵��~ID)<BR>
     * ���ꎷ�s�����戵��~ID<BR>
     */
    public String triggerTradeStopId;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password = null;
    
    /**
     * @@roseuid 4430D2D102FD
     */
    public WEB3AdminToTradeStopDelCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@���ꎷ�s�����戵��~ID�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.���ꎷ�s�����戵��~ID == null�̏ꍇ�A<BR>
     * �@@�@@�u���ꎷ�s�����戵��~ID�������́v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02430<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44223F4B02DA
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@���ꎷ�s�����戵��~ID�`�F�b�N
        //�@@�P�|�P�j�@@this.���ꎷ�s�����戵��~ID == null�̏ꍇ�A
        //�@@�@@�u���ꎷ�s�����戵��~ID�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.triggerTradeStopId))
        {
            log.debug("���ꎷ�s�����戵��~ID�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02430,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ꎷ�s�����戵��~ID�������͂ł��B");
        }            
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�폜�������X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopDelCompleteResponse(this);
    }
}@
