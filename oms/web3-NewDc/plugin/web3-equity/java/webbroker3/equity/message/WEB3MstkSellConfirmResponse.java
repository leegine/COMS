head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����m�F���X�|���X�N���X(WEB3MstkSellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * �i�����~�j�������t�����m�F���X�|���X�j�B<BR>
 * <br>
 * �����~�j�������t�����m�F���X�|���X�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellConfirmResponse extends WEB3MstkConfirmCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_sellConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * @@roseuid 4167B0500330
     */
	public WEB3MstkSellConfirmResponse() 
	{

	}
    public WEB3MstkSellConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
