head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityClosingContractSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ԍώw����(WEB3EquityClosingContractSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeClosingContractSpecImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;

/**
 * �i�����ԍώw����j�B<BR>
 * <BR>
 * �����ԍώw�����\������B
 * @@author ������
 * @@version 1.0
 * xTrade��EqTypeClosingContractSpecImpl���g�������N���X�B<BR>
 */
public class WEB3EquityClosingContractSpec extends EqTypeClosingContractSpecImpl 
{
     
    /**
     * (�����ԍώw����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �s�I�u�W�F�N�g�iEqTypeClosingContractSpecParams�j���w�肵
     * �X�[�p�[�N���X�̃R���X�g���N�^���R�[������B<BR>
     * @@param l_closingMarsinSpecInfoRow - �ԍώw����s�I�u�W�F�N�g<BR>
     * @@return WEB3EquityClosingContractSpec
     * @@roseuid 406A72A60362
     */
    public WEB3EquityClosingContractSpec(EqtypeClosingContractSpecRow l_closingMarsinSpecInfoRow) 
    {
        super(l_closingMarsinSpecInfoRow);
    }
}
@
