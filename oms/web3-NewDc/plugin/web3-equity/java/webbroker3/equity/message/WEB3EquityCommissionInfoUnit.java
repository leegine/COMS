head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������萔�����(WEB3EquityCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �K��(SRA) �V�K�쐬
*/

package webbroker3.equity.message;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������萔�����j�B<br>
 * <br>
 * ���������萔�����@@�f�[�^�N���X
 * @@version 1.0
 */

public class WEB3EquityCommissionInfoUnit extends Message
{
	
	/**
	 * (�萔���R�[�X�j<BR>
	 * 02�F��z�萔��(�X�^���_�[�h)�@@03�F��������v�@@04�F����<BR>
	 * 05�F�����z���@@12�F��z�萔��(�n�C�p�[�{�b�N�X)<BR>
	 */
	public String commissionCourse;

	/**
	 * (�萔��)<BR>
	 * �萔��<BR><BR>
	 */
	public String commission;

	/**
	 * (�萔�������)<BR>
	 * �萔�������<BR>
	 */
	public String commissionConsumptionTax;


	/**
	 * @@roseuid 
	 */
	public WEB3EquityCommissionInfoUnit()
	{

	}
	

}
@
