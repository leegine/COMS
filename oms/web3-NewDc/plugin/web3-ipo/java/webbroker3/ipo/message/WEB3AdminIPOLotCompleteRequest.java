head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�����������N�G�X�g(WEB3AdminIPOLotCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO���I�����������N�G�X�g)<BR>
 *  �Ǘ���IPO���I�����������N�G�X�g�N���X�B<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminIPOLotCompleteRequest extends WEB3IPOLotCommonRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIPOLotCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ǘ��҈Ïؔԍ��B
     */
    public String password;
    
    /**
     * ���I�V�[�P���X
     */
    public String lotSequence;
    
    /**
     * @@roseuid 4112DAD60041
     */
    public WEB3AdminIPOLotCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate()���R�[������B <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���R�[������B
        super.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60055
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotCompleteResponse(this);
    }
}
@
