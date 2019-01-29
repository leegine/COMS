head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p��������V�K�����̓��N�G�X�g�N���X(WEB3MarginOpenMarginChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������V�K�����̓��N�G�X�g�j�B<br>
 * <br>
 * �M�p��������V�K�����̓��N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeInputRequest extends WEB3GenRequest 
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3MarginOpenMarginChangeInputRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�����h�c)
     */
    public String id;
    
    /**
     * @@roseuid 4140453A0070
     */
    public WEB3MarginOpenMarginChangeInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866D27006C
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        if (id==null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140453A0084
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3MarginOpenMarginChangeInputResponse(this);
    }
}
@
