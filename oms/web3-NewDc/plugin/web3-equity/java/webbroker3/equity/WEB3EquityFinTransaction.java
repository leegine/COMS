head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFinTransaction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ڋq����N���X(WEB3EquityFinTransaction.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 �����@@���F(SRA) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionImpl;

/**
 * �i�����ڋq����j�B<BR>
 * <BR>
 * ���ٓ����ׂ�\������B<BR>
 * xTrade��EqTypeFinTransaction���g�������N���X�B
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3EquityFinTransaction extends EqTypeFinTransactionImpl
{

    /**
     * (�R���X�g���N�^�B)<BR>
     *<BR> 
     * @@param l_lngFinTransactionId
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3EquityFinTransaction(long l_lngFinTransactionId)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngFinTransactionId);
    }

    /**
     * (�R���X�g���N�^�B)<BR>
     *<BR>
     * @@param l_row EqtypeFinTransactionRow�I�u�W�F�N�g
     */
    public WEB3EquityFinTransaction(EqtypeFinTransactionRow l_row)
    {
        super(l_row);
    }
}
@
