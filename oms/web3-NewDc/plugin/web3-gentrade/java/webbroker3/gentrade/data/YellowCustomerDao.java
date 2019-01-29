head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	YellowCustomerDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link YellowCustomerDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link YellowCustomerRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YellowCustomerPK 
 * @@see YellowCustomerRow 
 */
public class YellowCustomerDao extends DataAccessObject {


  /** 
   * ����{@@link YellowCustomerDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private YellowCustomerRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link YellowCustomerRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link YellowCustomerDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link YellowCustomerDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link YellowCustomerRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof YellowCustomerRow )
                return new YellowCustomerDao( (YellowCustomerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a YellowCustomerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link YellowCustomerRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link YellowCustomerRow}�I�u�W�F�N�g 
    */
    protected YellowCustomerDao( YellowCustomerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link YellowCustomerRow}�I�u�W�F�N�g���擾���܂��B
   */
    public YellowCustomerRow getYellowCustomerRow() {
        return row;
    }


  /** 
   * �w���{@@link YellowCustomerRow}�I�u�W�F�N�g����{@@link YellowCustomerDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link YellowCustomerRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link YellowCustomerDao}�擾�̂��߂Ɏw���{@@link YellowCustomerRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link YellowCustomerDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static YellowCustomerDao forRow( YellowCustomerRow row ) throws java.lang.IllegalArgumentException {
        return (YellowCustomerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link YellowCustomerRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link YellowCustomerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link YellowCustomerPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link YellowCustomerParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( YellowCustomerRow.TYPE );
    }


  /** 
   * {@@link YellowCustomerRow}����ӂɓ��肷��{@@link YellowCustomerPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link YellowCustomerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link YellowCustomerParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link YellowCustomerPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static YellowCustomerPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link YellowCustomerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_eraBorn �����Ώۂł���p_eraBorn�t�B�[���h�̒l
   * @@param p_bornDate �����Ώۂł���p_bornDate�t�B�[���h�̒l
   * @@param p_nameKana �����Ώۂł���p_nameKana�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link YellowCustomerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static YellowCustomerRow findRowByPk( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerPK pk = new YellowCustomerPK( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name );
        return findRowByPk( pk );
    }


  /** 
   * �w���YellowCustomerPK�I�u�W�F�N�g����{@@link YellowCustomerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����YellowCustomerPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link YellowCustomerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static YellowCustomerRow findRowByPk( YellowCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (YellowCustomerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(YellowCustomerRow)}���g�p���Ă��������B 
   */
    public static YellowCustomerDao findDaoByPk( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerPK pk = new YellowCustomerPK( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name );
        YellowCustomerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(YellowCustomerPK)}�����{@@link #forRow(YellowCustomerRow)}���g�p���Ă��������B 
   */
    public static YellowCustomerDao findDaoByPk( YellowCustomerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        YellowCustomerRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and �ɂĎw��̒l�����ӂ�{@@link YellowCustomerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_eraBorn �����Ώۂł���p_eraBorn�t�B�[���h�̒l
   * @@param p_bornDate �����Ώۂł���p_bornDate�t�B�[���h�̒l
   * @@param p_nameKana �����Ώۂł���p_nameKana�t�B�[���h�̒l
   * @@param p_name �����Ώۂł���p_name�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name, and �̒l�ƈ�v����{@@link YellowCustomerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static YellowCustomerRow findRowByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            YellowCustomerRow.TYPE,
            "institution_code=? and era_born=? and born_date=? and name_kana=? and name=?",
            null,
            new Object[] { p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (YellowCustomerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query YellowCustomerDao.findRowsByInstitutionCodeEraBornBornDateNameKanaName(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeEraBornBornDateNameKanaName(String, String, String, String, String)}�����{@@link #forRow(YellowCustomerRow)}���g�p���Ă��������B 
   */
    public static YellowCustomerDao findDaoByInstitutionCodeEraBornBornDateNameKanaName( String p_institutionCode, String p_eraBorn, String p_bornDate, String p_nameKana, String p_name ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeEraBornBornDateNameKanaName( p_institutionCode, p_eraBorn, p_bornDate, p_nameKana, p_name ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
