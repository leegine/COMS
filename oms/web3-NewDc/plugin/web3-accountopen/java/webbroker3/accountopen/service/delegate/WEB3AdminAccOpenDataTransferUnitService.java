head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService(WEB3AdminAccOpenDataTransferUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 181  187
Revision History : 2009/08/26 �И���(���u) ���f�� 190
Revision History : 2009/08/31 ���g(���u) ���f�� 203
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.common.WEB3BaseException;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService�C���^�t�F�[�X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public interface WEB3AdminAccOpenDataTransferUnitService extends Service
{
    /**
     * �����J�݈ڊǂ̏������s���B<BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A8278DF033C
     */
    public void process(WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException;
}
@
