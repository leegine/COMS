head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBatoUrlRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�d�q��URL�擾���N�G�X�g(WEB3IPOBatoUrlRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 ���g(���u) �V�K�쐬
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (IPO�d�q��URL�擾���N�G�X�g)<BR>
 * IPO�d�q��URL�擾���N�G�X�g�N���X
 * 
 * @@author ���g
 * @@version 1.0
 */
public class WEB3IPOBatoUrlRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_batoUrl";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200601261130L;
  
    /**
     * @@roseuid 43D8344E02DE
     */
    public WEB3IPOBatoUrlRequest() 
    {
     
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EDC5004A
     */
    public WEB3GenResponse createResponse() 
    {
       return new WEB3IPOBatoUrlResponse(this);
    }    

}
@
