head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z���X�|���X�N���X(WEB3EquityOrderCarryOverResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 羐� (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�T
                   2004/12/21 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * �i���������J�z���X�|���X�j�B<br>
 * <br>
 * ���������J�z���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverResponse extends WEB3BackResponse
{

    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "equity_carry_over";

    /**
     * <p>�iserialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200405211030L;

    /**
     * <p>�i���������J�z���X�|���X�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     * @@param l_request ���������J�z���N�G�X�g
     */
    public WEB3EquityOrderCarryOverResponse(WEB3EquityOrderCarryOverRequest l_request)
    {
        super(l_request);
    }

    /**
     * <p>�i���������J�z���X�|���X�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3EquityOrderCarryOverResponse()
    {

    }    
}
@
