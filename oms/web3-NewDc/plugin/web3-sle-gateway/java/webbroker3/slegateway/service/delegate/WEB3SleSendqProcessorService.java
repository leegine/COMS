head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleSendqProcessorService�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 �� �V�K�쐬
*/
package webbroker3.slegateway.service.delegate;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.WEB3SleProcessors;

/**
 * SEND_Q���M�����N���X�̃C���^�t�F�[�X
 */
public interface WEB3SleSendqProcessorService extends Service{
  /**
   * �w�肵��SEND_Q ���b�Z�[�W��GlSleRequest�I�u�W�F�N�g�֕ϊ����A SLE�R�l�N�^�֓]������
   * 
   * @@param row
   * @@throws DataException
   */  
  public boolean processRow(Row l_row,final WEB3SleProcessors l_wsp, final WEB3SleConnectorClientFactory l_connectorfactory) throws DataException;	 
}
@
