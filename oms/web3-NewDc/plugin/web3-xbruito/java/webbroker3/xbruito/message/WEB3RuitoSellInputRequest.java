head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������̓��N�G�X�g�N���X(WEB3RuitoSellInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �ݓ������̓��N�G�X�g<BR>
 */
public class WEB3RuitoSellInputRequest extends WEB3GenRequest
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoSellInputRequest.class);	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * �����R�[�h<BR>
     * �ݐϓ����t�@@���h�R�[�h�B<BR>
     */
    public String ruitoProductCode;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 40762D0E01DE
     */
    public WEB3RuitoSellInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@ this.�����R�[�h��null�̒l�ł���Η�O���X���[����B<BR>
     *      class    : WEB3BusinessLayerException<BR>
     *      tag      : BUSINESS_ERROR_00079<BR>
     *      code     : 90<BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4092335900DA
     */
    public void validate() throws WEB3BusinessLayerException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);   

        //�����R�[�h��null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.ruitoProductCode))
        {
            log.debug("�����R�[�h�����͂���Ă��܂���B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�����R�[�h�����͂���Ă��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ������̓��X�|���X���쐬����<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40762B9B01DE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoSellInputResponse(this);
    }
}
@
