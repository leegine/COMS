head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMenuDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���j���[��ʕ\�����N�G�X�g(WEB3GentradeMenuDisplayRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ���j���[��ʕ\�����N�G�X�g�N���X
 */
public class WEB3GentradeMenuDisplayRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_menu_display";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200503151415L;
   
    /**
     * @@roseuid 423698A6008A
     */
    public WEB3GentradeMenuDisplayRequest() 
    {
    }
   
    /**
     * ���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��ԋp����B<br />
     * @@return WEB3GenResponse<br />
     * @@roseuid 42366FBD03A6<br />
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3GentradeMenuDisplayResponse(this);
    }
}
@
