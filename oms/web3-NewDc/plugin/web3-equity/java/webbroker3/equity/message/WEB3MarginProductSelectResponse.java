head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����������I�����X�|���X(WEB3MarginProductSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����V�K�����������I�����X�|���X�j�B<br>
 * <br>
 * �M�p����V�K�����������I�����X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginProductSelectResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_productSelect";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�s��R�[�h�̈ꗗ)
     */
    public String[] marketList;
    
    /**
     * (����I���x��������\������s��R�[�h�̈ꗗ)
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 4140477E033B
     */
    public WEB3MarginProductSelectResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginProductSelectResponse(WEB3MarginProductSelectRequest l_request)
    {
        super(l_request);
    }
}
@
