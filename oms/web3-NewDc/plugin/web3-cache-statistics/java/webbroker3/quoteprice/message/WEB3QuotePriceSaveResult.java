head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceSaveResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���X�|���X�N���X(WEB3QuotePriceSaveResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 �� (FLJ)�V�K�쐬
 */

package webbroker3.quoteprice.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * �i���X�|���X�N���X�X�N���X�j�B<br>
 * <br>
 * ���X�|���X�N���X
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceSaveResult
    extends Message
{

    /**
     * (��ЃR�[�h)
     */
    public String institutionCd;

    /**
     * (������)
     */
    public Long success;

    /**
     * (���s��)
     */
    public Long failure;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3QuotePriceSaveResult()
    {
    }

}
@
