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
filename	WEB3DocumentDeliverRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t���N�G�X�g(WEB3DocumentDeliverRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 ���V��@@(SRA) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���ʌ�t���N�G�X�g)<BR>
 * <BR>
 * ���ʌ�t���N�G�X�g<BR>
 * @@author ���V��@@
 * @@version 1.0
 */
public class WEB3DocumentDeliverRequest extends WEB3GenRequest 
{

    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "document_deliver";
    
    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200709281831L;

	/**
	 * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
	 */
	public WEB3GenResponse createResponse() 
    {
		return new WEB3DocumentDeliverResponse(this);
	}

}
@
