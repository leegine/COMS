head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRequestPreparer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleRequestPreparer�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 �� �V�K�쐬
 Revision History : 2006/06/09 ��(FLJ) �\�[�X����
*/
package webbroker3.slegateway.service.delegate.stdimpls;

import com.fitechlabs.xbconnector.glbase.gldata.GlSleRequest;
import webbroker3.slebase.data.SleSendQRow;

/**
 * SLE�R�l�N�^�փ��N�G�X�g�𑗐M����O�̏��������C���^�t�F�[�X.
 */
public interface WEB3SleRequestPreparer {

	/**
	 * SLE�R�l�N�^�փ��N�G�X�g�𑗐M����O�̓d���ϊ�����.
	 *
	 * @@param sendqRow  ma_sle_send_q SEND_Q���b�Z�[�W
	 * @@return  GlSleRequest GlSleRequest�d���I�u�W�F�N�g.
	 */
	public GlSleRequest prepare(SleSendQRow sendqRow);
}
@
