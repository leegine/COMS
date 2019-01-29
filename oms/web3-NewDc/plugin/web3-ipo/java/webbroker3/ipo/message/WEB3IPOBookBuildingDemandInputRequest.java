head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�N���X(WEB3IPOBookBuildingDemandInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�N���X
 * 
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171831L;
    
    /**
     * IPO�����h�c
     */
    public String id;
    
    /**
     * �d�q���`�F�b�N�t���O<BR>
     * <BR>
     * true�F�`�F�b�N���� <BR>
     * false�F�`�F�b�N���Ȃ� <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h�iIPO�ژ_�����j<BR>
     */
    public String typeCode;
    
    /**
     * @@roseuid 4112EA850184
     */
    public WEB3IPOBookBuildingDemandInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EA8501B6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingDemandInputResponse(this);
    }
}
@
