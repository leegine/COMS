head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm���X�|���X�N���X(WEB3MarginSwapOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i�M�p����������n�����ʒm���X�|���X�N���X�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm���X�|���X�N���X�B
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyResponse extends WEB3BackResponse 
{
    /**
     * (PTYPE)�B<BR>
     */
    public static final String PTYPE = "margin_swapOrderNotify";


    /**
     * (SerialVersionUID)�B<BR>
     */
    public static final long serialVersionUID = 200412241500L;


    /**
     * (�R���X�g���N�^)�B
     */
    public WEB3MarginSwapOrderNotifyResponse() 
    {
    }


    /**
     * (�R���X�g���N�^)�B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginSwapOrderNotifyResponse(WEB3MarginSwapOrderNotifyRequest l_request)
    {
       super(l_request);
    }
}
@
