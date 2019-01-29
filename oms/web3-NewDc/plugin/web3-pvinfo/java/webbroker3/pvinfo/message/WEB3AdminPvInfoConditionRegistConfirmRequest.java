head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g(WEB3AdminPvInfoConditionRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/26 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�\���ݒ�o�^�m�F���N�G�X�g�N���X
 * @@author �����F
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionRegistConfirmRequest";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����e���)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���͓��e�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�����e���.validate()���R�[������B<BR>
     * @@roseuid 415C024901D0
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        String STR_METHOD_NAME = " validate()";        
        log.entering(STR_METHOD_NAME);
        
        //�P�j���͓��e�̃`�F�b�N
        this.displayContentsUnit.validate();
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327BF0186
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionRegistConfirmResponse(this);
    }
}
@
