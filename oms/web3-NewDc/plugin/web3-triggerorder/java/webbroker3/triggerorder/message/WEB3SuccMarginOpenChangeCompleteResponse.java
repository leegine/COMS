head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginOpenChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p������������V�K���������X�|���X�N���X(WEB3SuccMarginOpenChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A����(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;

/**
 * (�i�A���j�M�p������������V�K���������X�|���X�N���X)<BR>
 * �i�A���j�M�p������������V�K���������X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3SuccMarginOpenChangeCompleteResponse extends WEB3MarginOpenMarginChangeCompleteResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginOpenChangeComplete";
    
    /**
     * @@roseuid 4369CC3F01B5
     */
    public WEB3SuccMarginOpenChangeCompleteResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccMarginOpenChangeCompleteResponse(WEB3SuccMarginOpenChangeCompleteRequest l_request)
    {
        super(l_request);
    }     
}
@
