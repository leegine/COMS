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
filename	WEB3GentradeProspectusDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ژ_�����\���\�����N�G�X�g(WEB3GentradeProspectusDisplayRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �ژ_�����\�����N�G�X�g�N���X
 */
public class WEB3GentradeProspectusDisplayRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_prospectus_display";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200503151417L;
   
    /**
     * @@roseuid 423698A601D2
     */
    public WEB3GentradeProspectusDisplayRequest() 
    {
    
    }
   
    /**
     * ���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��ԋp����B<br />
     * @@return WEB3GenResponse<br />
     * @@roseuid 4236708603DE<br />
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3GentradeProspectusDisplayResponse(this);
    }
}
@
